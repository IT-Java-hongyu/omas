package com.fosustu.omas.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InputForSearchDoctor {
	private Apartment apartment; //科室
	private Date date;//时间
	private Title title;//医生级别
	private String symptoms;//症状
	private List<Doctor> doctor; //查询出来的医生列表
		
	public Apartment getApartment() {
		return apartment;
	}
	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Title getTitle() {
		if(title==null) {
			title=new Title();
		}
		return title;
	}
	public void setTitle(Title title) {
		this.title = title;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public List<Doctor> getDoctor() {
		if(doctor==null ) {
			doctor=new ArrayList<Doctor>();
		}
		return doctor;
	}
	public void setDoctor(List<Doctor> doctor) {
		this.doctor = doctor;
	}	
}
