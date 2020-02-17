package com.fosustu.omas.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fosustu.omas.pojo.Doctor;
import com.fosustu.omas.pojo.Pagination;
import com.fosustu.omas.service.DoctorService;
import com.fosustu.omas.utils.AjaxReturnUtil;

@Controller
@RequestMapping("doctor")
public class DoctorController {
	@Autowired
	private DoctorService doctorService;
	
	/**
	 * 医生分页
	 * @return
	 */
	@RequestMapping("listByPage")
	@ResponseBody
	public String listByPage(Pagination agination) {
		System.out.println(agination.getPage() + "***************************");
		
		System.out.println(agination.getRows());
		agination.setCurrIndex((agination.getPage()-1)*agination.getRows());
		System.out.println(agination.getCurrIndex());
		List<Doctor> doctorList = doctorService.listByPage(agination);
		int total = doctorService.getCount();
		
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", total);
		mapData.put("rows", doctorList);
		//把医生列表转JSON字符串
		//DisableCircularReferenceDetect禁用循环引用保护
		String listString = JSON.toJSONString(mapData, SerializerFeature.DisableCircularReferenceDetect);
		
		
		return listString;
	}
	
	/**
	 * 通过id获取医生
	 * @param id
	 * @return
	 */
	@RequestMapping("get")
	@ResponseBody
	public Doctor getDoctorById(String id) {
		
		
		return doctorService.getDoctorById(id);
		
	}
	/**
	 * 通过id修改医生
	 * @param id
	 * @return
	 */
	@RequestMapping("updatedoctorById")
	@ResponseBody
	public String updatedoctorById(Doctor doctor) {
		try {
			System.out.println(doctor + "******************");
			doctorService.updateDoctorById(doctor);
			return AjaxReturnUtil.ajaxReturn(true, "修改成功！");
		}catch (Exception e) {
			return AjaxReturnUtil.ajaxReturn(false, "修改失败！");
		}
		
	}
	
	/**
	 * 通过id删除医生
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String deleteApartmentById(Doctor doctor) {
		try {
			System.out.println(doctor.getDocId() + "******************");
			doctorService.deleteDoctorById(doctor);
			return AjaxReturnUtil.ajaxReturn(true, "删除成功！");
		}catch (Exception e) {
			return AjaxReturnUtil.ajaxReturn(false, "删除失败！");
		}
		
	}
	
	/**
	 * 添加医生
	 * @param id
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public String addDoctorById(Doctor doctor) {
		try {
			System.out.println(doctor.getDocId() + "******************");
			doctorService.addDoctor(doctor);
			return AjaxReturnUtil.ajaxReturn(true, "添加成功！");
		}catch (Exception e) {
			return AjaxReturnUtil.ajaxReturn(false, "添加失败！");
		}
		
	}
	
	/**
	 * 解决前端传入日期格式为String 与 后端 Date 数据  无法成功映射的问题
	 * @param request
	 * @param binder
	 */
	@InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	/**
	 * 导出数据
	 */
	@RequestMapping("export")
	public void export(HttpServletResponse response) {
		String filename = "";
		
		filename += "医生列表.xls";
		//响应对象
		
		try {
			//设置输出流,实现下载文件
			response.setHeader("Content-Disposition", "attachment;filename=" +
	                new String(filename.getBytes(),"ISO-8859-1"));
			doctorService.export(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 导入数据
	 */
	@RequestMapping("doImport")
	@ResponseBody
	public String doImport(@RequestParam("file") CommonsMultipartFile file){
		System.out.println("-------" +file);
		//文件类型判断
		if(!"application/vnd.ms-excel".equals(file.getContentType())){
			return AjaxReturnUtil.ajaxReturn(false, "上传的文件必须为excel文件");
		}
		System.out.println(file.getOriginalFilename()+"++++++++++++++++");
		try {
			doctorService.doImport(file.getInputStream());
			return AjaxReturnUtil.ajaxReturn(true, "上传的文件成功");
			 		
		}catch (IOException e) {
			return AjaxReturnUtil.ajaxReturn(false, "上传的文件失败");
		} catch (Exception e) {
			return AjaxReturnUtil.ajaxReturn(false, "excel文本格式不对");
		}
	}

	
}
