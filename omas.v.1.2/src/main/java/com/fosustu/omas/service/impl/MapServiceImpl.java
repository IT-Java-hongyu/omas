package com.fosustu.omas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosustu.omas.mapper.MapMapper;
import com.fosustu.omas.pojo.Maps;
import com.fosustu.omas.service.MapService;
@Service
public class MapServiceImpl implements MapService {

	@Autowired
	private MapMapper _mapMapper;
	@Override
	public Maps searchMap(Maps map) {
		
		return _mapMapper.searchMap(map);
	}

}
