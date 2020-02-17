package com.fosustu.omas.mapper;

import java.util.List;

import com.fosustu.omas.pojo.CardInform;
import com.fosustu.omas.pojo.Doctor;
import com.fosustu.omas.pojo.Pagination;
import com.fosustu.omas.pojo.Patient;

public interface PatientMapper {
	List<Patient> listByPage(Pagination agination); //病人分页
	
	Patient getPatientById(String id);              //通过id查找病人
	
	void updatePatientById(Patient patient);        //通过id更新病人
	
	void deletePatientById(Patient patient);         //通过id删除病人
	
	void addPatient(Patient patient);                //新增病人
	 
	int getCount();                                   //获取病人总数
	
	List<Patient> getAllPatient();                    //获取所有病人列表
	
	void InsertPatient(String patientId);            //新增病人

	String getPatientCardById(String patientId);	//获取医疗卡信息

	void updataCardId(CardInform cardInform );			//更新医疗卡信息
}