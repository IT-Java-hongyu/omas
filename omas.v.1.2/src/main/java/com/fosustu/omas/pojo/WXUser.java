package com.fosustu.omas.pojo;

import java.util.Date;

public class WXUser {

	private String wxID;
	private String openid;
	private String sessionKey;
	private String token;
	private String patientId;
	private Date tokenTime;
	
	public Date getTokenTime() {
		return tokenTime;
	}
	public void setTokenTime(Date tokenTime) {
		this.tokenTime = tokenTime;
	}
	public String getWxID() {
		return wxID;
	}
	public void setWxID(String wxID) {
		this.wxID = wxID;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
}
