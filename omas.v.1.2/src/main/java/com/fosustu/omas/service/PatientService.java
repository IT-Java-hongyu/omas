package com.fosustu.omas.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;

import com.fosustu.omas.pojo.CardInform;
import com.fosustu.omas.pojo.Doctor;
import com.fosustu.omas.pojo.InputForSearchDoctor;
import com.fosustu.omas.pojo.Pagination;
import com.fosustu.omas.pojo.Patient;
import com.fosustu.omas.pojo.Record;

public interface PatientService {
	Record SearchSymptoms(String pid);    
	boolean UpdateSymptomsOrTrack(Record r,int sign)throws ParseException;
	//查科室
	InputForSearchDoctor SearchDoctorList(InputForSearchDoctor condition);
	
	List<Patient> listByPage(Pagination agination);    //病人分页
	Patient getPatientById(String id);                 //通过id查找病人
    void updatePatientById(Patient patient);           //通过id更新病人
    void deletePatientById(Patient patient);          //通过id删除病人
    void addPatient(Patient patient);                  //新增病人
    int getCount();                                    //获取病人总数
    
    public void export(OutputStream os);               //导出病人信息

    public void doImport(InputStream is);              //导入病人信息
	String getPatientCardById(String patientId);        //获取医疗卡id
	void updataCardId(CardInform cardInform);	//更新医疗卡id
}
