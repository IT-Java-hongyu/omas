package com.fosustu.omas.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicineTime {
	private String pid;
	private Date startTime;
	private String medicineNames;
	private List<Date> medicineTime = new ArrayList<Date>();
	private String usage;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getMedicineNames() {
		return medicineNames;
	}
	public void setMedicineNames(String medicineNames) {
		this.medicineNames = medicineNames;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public List<Date> getMedicineTime() {
		return medicineTime;
	}
	public void setMedicineTime(List<Date> medicineTime) {
		this.medicineTime = medicineTime;
	}
	
	

}
