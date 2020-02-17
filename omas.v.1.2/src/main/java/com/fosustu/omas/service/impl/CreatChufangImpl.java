package com.fosustu.omas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fosustu.omas.mapper.PrescriptionMapper;
import com.fosustu.omas.pojo.Prescription;
import com.fosustu.omas.service.CreatChufang;

@Service
@Transactional
public class CreatChufangImpl implements CreatChufang {

	@Autowired
	private  PrescriptionMapper prescriptionMapper;
	
	/*
	 * 获取处方信息及药物列表
	 * (non-Javadoc)
	 * @see com.fosustu.omas.service.CreatChufang#getChufangMedicineList(java.lang.String, java.lang.String)
	 */
	@Override
	public Prescription getChufangMedicineList(String patientId, String recordId) {
		// TODO Auto-generated method stub
		return prescriptionMapper.getChufangMedicineList(patientId, recordId);
	}

}
