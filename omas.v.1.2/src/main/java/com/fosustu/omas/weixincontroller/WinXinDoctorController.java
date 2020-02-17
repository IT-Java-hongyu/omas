package com.fosustu.omas.weixincontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fosustu.omas.pojo.Score;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fosustu.omas.pojo.Doctor;
import com.fosustu.omas.pojo.DoctorList;
import com.fosustu.omas.service.DoctorService;

@Controller
@RequestMapping("wxDoctor")
public class WinXinDoctorController {
	@Autowired
	private DoctorService doctorService;
	
	@RequestMapping("getDoctorByApartId")
	public @ResponseBody DoctorList getDoctorByApartId(String apartId) {
		List<Doctor> doctordd = doctorService.getDoctorByApartId(apartId);
		DoctorList d = new DoctorList();
		d.setDocList(doctordd);
		return d;
	}

	@RequestMapping("getRankedDoctorByApartId")
	public @ResponseBody DoctorList getRankedDoctorByApartId(String apartId) {
		List<Doctor> doctors = doctorService.getRankedDoctorByApartId(apartId);
		DoctorList d = new DoctorList();
		d.setDocList(doctors);
		return d;
	}
	@RequestMapping("getAllDoctor")
	public @ResponseBody DoctorList getAllDoctor() {
		List<Doctor> doctordd = doctorService.getAllDoctor();
		DoctorList d = new DoctorList();
		d.setDocList(doctordd);
		return d;
	}
	@RequestMapping("getAllDoctorInformation")
	public @ResponseBody DoctorList getAllDoctorInformation() {
		List<Doctor> doctordd = doctorService.getAlldoctorInformation();
		DoctorList d = new DoctorList();
		d.setDocList(doctordd);
		return d;
	}
	@RequestMapping("updataDoctorScoreById")
	public @ResponseBody Map<String,String> updataDoctorScoreById(@RequestParam String docId,@RequestParam String score){
		System.out.println(docId);
		Doctor doctor = new Doctor();
		doctor.setDocId(docId);
		doctor.setScore(score);
		doctorService.updateDoctorScoreById(doctor);
		Map map = new HashMap();
		map.put("status","200");
		return map;
	}
}
