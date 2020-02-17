package com.fosustu.omas.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.fosustu.omas.mapper.DoctorMapper;
import com.fosustu.omas.pojo.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fosustu.omas.mapper.GuaHaoMapper;
import com.fosustu.omas.pojo.GuaHao;
import com.fosustu.omas.pojo.Patient;
import com.fosustu.omas.service.GuaHaoService;

@Service
@Transactional
public class GuaHaoServiceImpl implements GuaHaoService {

	@Autowired
	private GuaHaoMapper guaHaoMapper;
	
	@Override
	public int[] guaHao(String apartId, String doctorId, String patientId) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		String nowTime = dateFormat.format(date);
		
		GuaHao guahao = new GuaHao();
		guahao.setApartId(apartId);
		guahao.setDoctorId(doctorId);
		guahao.setPatientId(patientId);
		guahao.setGuahaoBiaoZhi(1);         //1为挂号
		guahao.setGuahaoDate(nowTime);
		
		guaHaoMapper.saveGuahao(guahao);  //保存挂号信息
		int guaHaoId = guaHaoMapper.findGuahaoIdBypatientIdAndGuahaoBiaoZhi(patientId); //通过病人id和挂号标志获得挂号Id码
		int guahaoPatientNum = guaHaoMapper.findPatientNum(guaHaoId,doctorId); //通过挂号id和挂号标志判断当前挂号人数，以获得排队数
		int guahaoStatus = guaHaoMapper.getGuahaoStatus(patientId);  //挂号状态
		
		
		int[] res = new int[3];
		res[0] = guaHaoId;
		res[1] = guahaoPatientNum;
		res[2] = guahaoStatus;
		return res;
	}

	@Override
	public Patient jiaohao(String doctorId) {
		Patient p= guaHaoMapper.jiaohao(doctorId); //叫号当前医生下挂号的病人（挂号码最小的病人优秀）
		
		int guahaoNum =  guaHaoMapper.findGuahaoIdBypatientIdAndGuahaoBiaoZhi(p.getPatientId());
		guaHaoMapper.changeGuaHaoStatus(guahaoNum);  //改变挂号状态为已叫号
		p.setGuahaoNum(guahaoNum);
		return p;
	}

	@Override
	public int getGuahaoStatus(String patientId) {
		// TODO Auto-generated method stub
		return guaHaoMapper.getGuahaoStatus(patientId);
	}

	@Override
	public int findGuahaoIdBypatientIdAndGuahaoBiaoZhi(String patientId) {
		// TODO Auto-generated method stub
		return guaHaoMapper.findGuahaoIdBypatientIdAndGuahaoBiaoZhi(patientId);
	}

	@Override
	public int findPatientNum(int guahaoId , String doctorId) {
		// TODO Auto-generated method stub
		return guaHaoMapper.findPatientNum(guahaoId,doctorId);
	}

	@Override
	public Doctor getDoctorByPatientId(String patientId) {
		// TODO Auto-generated method stub
		Doctor doctor = guaHaoMapper.getDoctorByPatientId(patientId);
		System.out.println(doctor.getDocName());
		return doctor;
	}

	@Override
	public String getApartmentNameByPatientId(String patientId) {
		// TODO Auto-generated method stub
		return guaHaoMapper.getApartmentNameByPatientId(patientId);
	}

}
