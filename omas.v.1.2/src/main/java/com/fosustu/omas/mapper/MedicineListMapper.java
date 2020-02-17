package com.fosustu.omas.mapper;

import java.util.List;

import com.fosustu.omas.pojo.MedicineList;

public interface MedicineListMapper {

	
	void saveMedicineList(MedicineList ml); //保存药物列表
	
	List<MedicineList> getMedicineList(String rec_id);
   
}