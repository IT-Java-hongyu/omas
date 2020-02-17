package com.fosustu.omas.service;

import com.fosustu.omas.pojo.Prescription;

public interface CreatChufang {
	
	public Prescription getChufangMedicineList(String patientId , String recordId);  //获取处方信息
}
