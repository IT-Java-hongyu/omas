package com.fosustu.omas.mapper;

import com.fosustu.omas.pojo.Apartment;
import com.fosustu.omas.pojo.InputForSearchDoctor;

import java.util.List;

import com.fosustu.omas.pojo.Pagination;


public interface ApartmentMapper {

//通过科室典型症状查找科室
	Apartment SearchBySymptoms(InputForSearchDoctor condition);
  //通过医生特长找到科室
  Apartment SearchApartmentByDocSpeciality(String symptoms);
	//通过症状表和症状对比找到科室
	Apartment SearchApartmentBySymptoms(String symptoms);
	List<Apartment> listByPage(Pagination agination);  //部门分页
	
	Apartment getApartementById(String id);           //通过id获取部门
	
	void updateApartmentById(Apartment apartment);    //更新部门
	
	void deleteApartmentById(Apartment apartment);    //删除部门
	
	void addApartment(Apartment apartment);           //新增部门
	List<Apartment> getAllApartment();                //获取部门列表
	 
	int getCount();                                   //获取部门总数
}