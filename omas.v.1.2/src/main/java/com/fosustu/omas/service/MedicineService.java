package com.fosustu.omas.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fosustu.omas.pojo.Medicine;

public interface MedicineService {
	public List<Medicine> findMedicineByMedicineName(String medicineName);    //通过药物名获取药物信息
	public List<Medicine> findMedicineByMedicineSyptoms(String syptoms);  //通过药物症状获取药物信息
	//保存药物列表和处方信息
	public void saveMedicineListAndChufang(HttpServletRequest request,String medicinePriceString ,String medicineIdString, String medicineNumString , String medicineUsageString , String medicineZengduanResult); 
	
}
