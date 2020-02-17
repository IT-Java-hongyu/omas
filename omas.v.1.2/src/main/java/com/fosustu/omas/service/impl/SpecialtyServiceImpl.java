package com.fosustu.omas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fosustu.omas.mapper.SpecialtyMapper;
import com.fosustu.omas.pojo.Specialty;
import com.fosustu.omas.service.SpecialtyService;

@Service
@Transactional
public class SpecialtyServiceImpl implements SpecialtyService{

	@Autowired
	private SpecialtyMapper specialtyMapper;
	@Override
	public List<Specialty> getAllSpecialty() {
		// TODO Auto-generated method stub
		return specialtyMapper.getAllSpecialty();
	}

}
