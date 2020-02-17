package com.fosustu.omas.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fosustu.omas.mapper.RecordMapper;
import com.fosustu.omas.mapper.SymptomsMapper;
import com.fosustu.omas.pojo.Record;
import com.fosustu.omas.pojo.Symptoms;
import com.fosustu.omas.service.SymptomsService;

@Service
@Transactional
public class SymptomsServiceImpl implements SymptomsService {

	@Autowired
	private SymptomsMapper symptomsMapper; 
	
	@Autowired
	private RecordMapper recordMapper;
	
	/**
	 * 保存处方信息和药物列表
	 */
	@Override
	public void saveRecord(HttpServletRequest request,String symptoms, String cdString,String patientId, String doctorId , String symptomDetail) {
		//symptoms = symptoms.substring(1, symptoms.length()-1);
		String[] symptoms1 = symptoms.split(",");      //字符串截取成数组
		String[] cdString1 = cdString.split(",");
		for(String s : symptoms1)System.out.println(s +"*********************");
		
		String RecordUuid = UUID.randomUUID().toString().replaceAll("-", "");
		request.getSession().setAttribute("recordId", RecordUuid);
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String nowTime = dateFormat.format(now);//格式化然后放入字符串中
		Record record = new Record();
		record.setRecId(RecordUuid);
		record.setpId(patientId);
		record.setDocId(doctorId);
		record.setDate(nowTime);
		record.setStatus(1);
		record.setDescDetail(symptomDetail);
		recordMapper.saveRecord(record);
		
		for(int i=0;i<symptoms1.length;i++) {
			String symtomsUuid = UUID.randomUUID().toString().replaceAll("-", "");
			Symptoms symtom = new Symptoms();
			symtom.setSymId(symtomsUuid);
			symtom.setRecId(RecordUuid);
			symtom.setDesc(symptoms1[i]);
			symtom.setStartDegree(cdString1[i]);
			
			
			symptomsMapper.saveSymptoms(symtom);
		}
	}

}
