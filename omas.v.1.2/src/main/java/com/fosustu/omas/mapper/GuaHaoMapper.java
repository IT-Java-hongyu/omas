package com.fosustu.omas.mapper;

import com.fosustu.omas.pojo.Doctor;
import org.apache.ibatis.annotations.Param;

import com.fosustu.omas.pojo.GuaHao;
import com.fosustu.omas.pojo.Patient;

public interface GuaHaoMapper {
	public void saveGuahao(GuaHao guahao); //保存挂号信息
	public int findGuahaoIdBypatientIdAndGuahaoBiaoZhi(String patientId);  //通过病人id和挂号标志获得挂号Id码
	public int findPatientNum(@Param("guaHaoId") int guaHaoId ,@Param("doctorId") String doctorId);  //通过挂号id ,医生id，和挂号标志判断当前挂号人数，以获得排队数
	
	 public Patient jiaohao(String doctorId);  //叫号
	public void changeGuaHaoStatus(int guahaoNum);  //更改挂号状态
	public int getGuahaoStatus(String patientId);  //获取挂号状态
	public Doctor getDoctorByPatientId(String patientId);
	public String getApartmentNameByPatientId(String patientId);
}  
