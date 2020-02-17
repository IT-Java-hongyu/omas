package com.fosustu.omas.mapper;

import java.util.List;

import com.fosustu.omas.pojo.WXUser;

public interface WXUserMapper {

	List<WXUser> getUserByOpenId(String openid);
	boolean InsertWXUserMsg(WXUser wxUser);
	void updateTakenTime(WXUser wxUser);
	void updateTaken(WXUser wxUser);
	WXUser getUserByToken(String token);
}
