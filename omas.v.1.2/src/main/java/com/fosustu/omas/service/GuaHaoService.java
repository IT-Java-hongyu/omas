package com.fosustu.omas.service;

import com.fosustu.omas.pojo.Doctor;
import com.fosustu.omas.pojo.Patient;

public interface GuaHaoService {
    public int[] guaHao(String apartId , String doctorId , String patientId);
    public Patient jiaohao(String doctorId);
    public int getGuahaoStatus(String patientId);  //获取挂号状态
    public int findGuahaoIdBypatientIdAndGuahaoBiaoZhi(String patientId); //通过病人id和挂号标志获得挂号Id码
    public int findPatientNum(int guahaoId ,String doctorId);  //获取排队数
	public Doctor getDoctorByPatientId(String patientId); //获取医生姓名
	public String getApartmentNameByPatientId(String patientId);
}
