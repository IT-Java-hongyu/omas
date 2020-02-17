package com.fosustu.omas.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MedicineTimeInput {
	private String pid;
	private Date startTime;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.startTime = format.parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
