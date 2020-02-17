package com.fosustu.omas.mapper;

import java.util.List;

import com.fosustu.omas.pojo.Apartment;
import com.fosustu.omas.pojo.Symptoms;

public interface SymptomsMapper {
	//通过病历id找到症状列表
	List<Symptoms> SearchSymptoms(String recId);	
	//修改症状的最新状态
	void UpdateSymptoms(Symptoms symptoms); 
	
	void saveSymptoms(Symptoms symtom);
}