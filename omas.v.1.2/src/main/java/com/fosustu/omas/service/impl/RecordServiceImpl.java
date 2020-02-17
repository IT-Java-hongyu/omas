package com.fosustu.omas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosustu.omas.mapper.RecordMapper;
import com.fosustu.omas.pojo.Record;
import com.fosustu.omas.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService {

	@Autowired
	private RecordMapper _recordMapper;
	
	@Override
	public Record SearchNewRecord(String pid) {
		
		return _recordMapper.SearchNewRecord(pid);
	}

}
