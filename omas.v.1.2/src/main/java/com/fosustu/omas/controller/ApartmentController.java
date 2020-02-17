package com.fosustu.omas.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fosustu.omas.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fosustu.omas.pojo.Apartment;
import com.fosustu.omas.pojo.Pagination;
import com.fosustu.omas.service.ApartmentService;
import com.fosustu.omas.utils.AjaxReturnUtil;



/**
 *  部门类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("apartment")
public class ApartmentController {

	@Autowired
	private ApartmentService apartmentService;
	
	/**
	 * 部门分页
	 * @return
	 */
	@RequestMapping("listByPage")
	@ResponseBody
	public String listByPage(Pagination agination) {
		System.out.println(agination.getPage() + "***************************");
		
		System.out.println(agination.getRows());
		agination.setCurrIndex((agination.getPage()-1)*agination.getRows());
		System.out.println(agination.getCurrIndex());
		List<Apartment> apartmentList = apartmentService.listByPage(agination);
		int total = apartmentService.getCount();
		
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", total);
		mapData.put("rows", apartmentList);
		//把部门列表转JSON字符串
		//DisableCircularReferenceDetect禁用循环引用保护
		String listString = JSON.toJSONString(mapData, SerializerFeature.DisableCircularReferenceDetect);
		
		
		return listString;
	}
	
	/**
	 * 通过id获取部门
	 * @param id
	 * @return
	 */
	@RequestMapping("get")
	@ResponseBody
	public Apartment getApartementById(String id) {
		
		System.out.println("id++++"+id);
		return apartmentService.getApartementById(id);
		
	}

	@RequestMapping("getAllTags")
	@ResponseBody
	public List<Tag> getAllTags(){
		return apartmentService.getAllTags();
	}
	/**
	 * 通过id修改部门
	 * @param id
	 * @return
	 */
	@RequestMapping("updateapartmentById")
	@ResponseBody
	public String updateApartmentById(Apartment apartment) {
		try {
			System.out.println(apartment + "******************");
			apartmentService.updateApartmentById(apartment);
			return AjaxReturnUtil.ajaxReturn(true, "修改成功！");
		}catch (Exception e) {
			return AjaxReturnUtil.ajaxReturn(false, "修改失败！");
		}
		
	}
	
	/**
	 * 通过id删除部门
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String deleteApartmentById(Apartment apartment) {
		try {
			System.out.println(apartment.getApartId() + "******************");
			apartmentService.deleteApartmentById(apartment);
			return AjaxReturnUtil.ajaxReturn(true, "删除成功！");
		}catch (Exception e) {
			return AjaxReturnUtil.ajaxReturn(false, "删除失败！");
		}
		
	}
	
	/**
	 * 添加部门
	 * @param id
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public String addApartmentById(Apartment apartment) {
		try {
			System.out.println(apartment.getApartId() + "******************");
			apartmentService.addApartment(apartment);
			return AjaxReturnUtil.ajaxReturn(true, "添加成功！");
		}catch (Exception e) {
			return AjaxReturnUtil.ajaxReturn(false, "添加失败！");
		}
		
	}
	
	/**
	 * 导出数据
	 */
	@RequestMapping("export")
	public void export(HttpServletResponse response) {
		String filename = "";
		
		filename += "部门信息.xls";
		//响应对象
		
		try {
			//设置输出流,实现下载文件
			response.setHeader("Content-Disposition", "attachment;filename=" +
	                new String(filename.getBytes(),"ISO-8859-1"));
			apartmentService.export(response.getOutputStream());
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
		//System.out.println(file.getOriginalFilename()+"++++++++++++++++");
		try {
			apartmentService.doImport(file.getInputStream());
			return AjaxReturnUtil.ajaxReturn(true, "上传的文件成功");
		}catch (IOException e) {
			return AjaxReturnUtil.ajaxReturn(false, "上传的文件失败");
			
		} catch (Exception e) {
			return AjaxReturnUtil.ajaxReturn(false, "excel文本格式不对");
		}
	}
}
