package com.fosustu.omas.mapper;

import java.util.List;

import com.fosustu.omas.pojo.Medicine;

public interface MedicineMapper {
	public List<Medicine> findMedicineByMedicineName(String medicineName); //通过药物名查找药物
	public List<Medicine> findMedicineByMedicineSyptoms(String syptomsName);//通过药物症状获取药物
	
	Medicine searchMedicineNameById(String medicineId);
}