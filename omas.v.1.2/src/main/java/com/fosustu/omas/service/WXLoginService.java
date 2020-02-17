package com.fosustu.omas.service;

import java.util.List;

import com.fosustu.omas.pojo.WXUser;

public interface WXLoginService {

	List<WXUser> getUserByOpenId(String openid);
	boolean InsertWXUserMsg(WXUser wxUser);
	void updateTakenTime(WXUser wxUser);
	void updateTaken(WXUser wxUser);
	WXUser getUserByToken(String token);
}
