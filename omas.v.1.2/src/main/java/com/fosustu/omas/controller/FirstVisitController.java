package com.fosustu.omas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fosustu.omas.pojo.Doctor;
import com.fosustu.omas.pojo.Patient;
import com.fosustu.omas.pojo.Prescription;
import com.fosustu.omas.pojo.User;
import com.fosustu.omas.service.CreatChufang;
import com.fosustu.omas.service.GuaHaoService;
import com.fosustu.omas.service.MedicineService;
import com.fosustu.omas.service.SymptomsService;
import com.fosustu.omas.utils.AjaxReturnUtil;

@Controller
@RequestMapping("FirstVisitController")
public class FirstVisitController {
	
	@Autowired
	private SymptomsService symptomsService;
	
	@Autowired
	private MedicineService medicindeService;
	
	@Autowired 
	private CreatChufang creatChufang;  //service
	
	@Autowired
	private GuaHaoService guaHaoService;
	
	//保存病历
	@RequestMapping("saveRecord")
	@ResponseBody
	public String saveRecord(HttpServletRequest request ,String symptoms ,String cdString, String patientId ,String symptomDetail) {
		try {
			System.out.println(symptoms+"*******"+cdString+patientId+"*******"+symptomDetail);
			request.getSession().setAttribute("patientId", patientId);
			User loginUser= (User)request.getSession().getAttribute("CURR_USER");
			request.getSession().setAttribute("Symptoms", symptoms);
			symptomsService.saveRecord(request,symptoms,cdString, patientId, loginUser.getUserid(),symptomDetail);
			return AjaxReturnUtil.ajaxReturn(true, "病症录入成功");
		}catch (Exception e) {
			return AjaxReturnUtil.ajaxReturn(false, "病症录入失败");
		}
	}
	/**
	 * 保存药物列表和处方信息
	 * @param request
	 * @param medicindePriceString  药物价格
	 * @param medicineIdString      药物id
	 * @param medicineNumString     药物数量
	 * @param medicineUsageString   药物用法
	 * @param medicineZengduanResult  诊断结果
	 * @return
	 */
	@RequestMapping("saveMedicineList")
	@ResponseBody
	public String saveMedicineList(HttpServletRequest request,String medicindePriceString , String medicineIdString , String medicineNumString ,String medicineUsageString,String medicineZengduanResult) {
		try {
			System.out.println(medicindePriceString+"+++++++"+ medicineIdString + "+++" + medicineNumString +"-----" + medicineUsageString +"---"+medicineZengduanResult);
			medicindeService.saveMedicineListAndChufang(request , medicindePriceString  , medicineIdString,medicineNumString,medicineUsageString , medicineZengduanResult);
			return AjaxReturnUtil.ajaxReturn(true, "保存处方药物信息成功");
		} catch (Exception e) {
			return AjaxReturnUtil.ajaxReturn(false, "保存处方药物信息失败");
		}
		
	}
	
	/**
	 *  返回处方信息
	 * @param request
	 * @return
	 */
	@RequestMapping("CreateChufang")
	@ResponseBody
	public String CreateChufang(HttpServletRequest request) {
		String recordId = (String)request.getSession().getAttribute("recordId");

		String patientId = (String)request.getSession().getAttribute("patientId");
		Prescription c = creatChufang.getChufangMedicineList(patientId, recordId);
		System.out.println("++++++++++-----++++="+c.toString());
		return AjaxReturnUtil.ajaxReturn(true, JSON.toJSONString(c, true));
		
		
	}
	
	
	/**
	 * 叫号
	 */
	@RequestMapping("jiaohao")
	public @ResponseBody String jiaohao(HttpServletRequest request) {
		User u =(User)request.getSession().getAttribute("CURR_USER");
		//判断当前用户是否为医生
		
		
		try {
			Patient p = guaHaoService.jiaohao(u.getUserDetailId());
			return AjaxReturnUtil.ajaxReturn(true, JSON.toJSONString(p, true));
		}catch (Exception e) {
			return AjaxReturnUtil.ajaxReturn(false, "目前没有人挂号排队");
		}
		
		
	}
}
