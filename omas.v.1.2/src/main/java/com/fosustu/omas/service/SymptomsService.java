package com.fosustu.omas.service;

import javax.servlet.http.HttpServletRequest;

public interface SymptomsService {
	//保存处方信息
	public void saveRecord(HttpServletRequest request,String symptoms ,String cdString, String patientId , String doctorId , String symptomDetail);
}
