package com.fosustu.omas.weixincontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fosustu.omas.pojo.Specialty;
import com.fosustu.omas.pojo.SpecialtyList;
import com.fosustu.omas.service.SpecialtyService;

@Controller
@RequestMapping("wxSpecialty")
public class WXSpecialtyController {

	@Autowired
	private SpecialtyService specialtyService;
	
	@RequestMapping("getAllSpecialty")
	public @ResponseBody SpecialtyList getAllSpecialty() {
		List<Specialty> specialtyList = specialtyService.getAllSpecialty();
		SpecialtyList a = new SpecialtyList();
		a.setSpecialtyList(specialtyList);
		return a;
	}
}
