package com.fosustu.omas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fosustu.omas.pojo.Medicine;
import com.fosustu.omas.service.MedicineService;
import com.fosustu.omas.utils.AjaxReturnUtil;

@Controller
@RequestMapping("medicine")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;
	
	
	/**
	 *  通过药物名查找药物
	 * @param medicineName
	 * @return
	 */
	@RequestMapping("findMedicineByMedicineName")
	@ResponseBody
	public String findMedicineByMedicineName(String medicineName) {
		System.out.println("********"+medicineName);
		List<Medicine> medicineList = medicineService.findMedicineByMedicineName(medicineName);
		if(!medicineList.isEmpty()) {
			String medicineJson = JSON.toJSONString(medicineList, true);
			return AjaxReturnUtil.ajaxReturn(true, medicineJson);
		}else {
			return AjaxReturnUtil.ajaxReturn(false, "药房没有此药物");
		}
	}
	/**
	 * 通过病症查找药物
	 * @param request
	 * @return
	 */
	@RequestMapping("findMedicineByMedicineSyptoms")
	@ResponseBody
	public String findMedicineByMedicineSyptoms(HttpServletRequest request) {
		String sysptomString = (String) request.getSession().getAttribute("Symptoms");
		
		List<Medicine> medicineList = medicineService.findMedicineByMedicineSyptoms(sysptomString);
		if(!medicineList.isEmpty()) {
			String medicineJson = JSON.toJSONString(medicineList, true);
			return AjaxReturnUtil.ajaxReturn(true, medicineJson);
		}else {
			return AjaxReturnUtil.ajaxReturn(false, "无法根据此症状自动推荐药物，请医生手动查找");
		}
	}
}
