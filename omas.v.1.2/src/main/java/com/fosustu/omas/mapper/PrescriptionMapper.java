package com.fosustu.omas.mapper;

import java.util.List;

import com.fosustu.omas.pojo.ChufanMedicineList;
import com.fosustu.omas.pojo.Prescription;

public interface PrescriptionMapper {

	void saveChufang(Prescription pre);       //保存处方信息
	public Prescription getChufangMedicineList(String patientId , String recordId); //获取处方信息和药物列表
	public List<ChufanMedicineList> getChufanMedicineList();//获取处方信息列表
    
}