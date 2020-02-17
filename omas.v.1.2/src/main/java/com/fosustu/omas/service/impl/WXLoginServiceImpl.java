package com.fosustu.omas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fosustu.omas.mapper.PatientMapper;
import com.fosustu.omas.mapper.WXUserMapper;
import com.fosustu.omas.pojo.WXUser;
import com.fosustu.omas.service.WXLoginService;

@Transactional
@Service
public class WXLoginServiceImpl implements WXLoginService {

	@Autowired
	private WXUserMapper _wxUserMapper;
	@Autowired
	private PatientMapper _patientMapper;
	@Override
	public List<WXUser> getUserByOpenId(String openid) {
		
		return _wxUserMapper.getUserByOpenId(openid);
	}

	@Override
	public boolean InsertWXUserMsg(WXUser wxUser) {
		_wxUserMapper.InsertWXUserMsg(wxUser);
		_patientMapper.InsertPatient(wxUser.getPatientId());
		return true;
	}

	@Override
	public void updateTakenTime(WXUser wxUser) {
		_wxUserMapper.updateTakenTime(wxUser);		
	}

	@Override
	public void updateTaken(WXUser wxUser) {
		_wxUserMapper.updateTakenTime(wxUser);
	}

	@Override
	public WXUser getUserByToken(String token) {
		
		return _wxUserMapper.getUserByToken(token);
	}

}
