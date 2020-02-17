package com.fosustu.omas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/*
 *  页面跳转控制类
 */
@Controller
public class PageController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("login")
	public String loginPage() {
		return "login";
	}
	@RequestMapping("doctor")
	public String doctor() {
		return "doctor";
	}
	@RequestMapping("apartment")
	public String apartment() {
		return "apartment";
	}
	@RequestMapping("patient")
	public String patient() {
		return "patient";
	}
	
	@RequestMapping("firstVisit")
	public String firstVisit() {
		return "firstVisit";
	}
	@RequestMapping("UserRoleSet")
	public String UserRoleSet() {
		return "UserRoleSet";
	}
	@RequestMapping("RoleMenuSet")
	public String RoleMenuSet() {
		return "RoleMenuSet";
	}
	
	@RequestMapping("selectMedicient")
	public String  selectMedicient(String symptoms) {
//		System.out.println("selectMedicient(String symptoms)"+symptoms);
//		ModelAndView mv = new ModelAndView("selectMedicient");
//		mv.addObject("mvSymptoms", symptoms);
		return "selectMedicient";
	}
	@RequestMapping("chufang")
	public String chufang() {
		return "chufang";
	}
}
