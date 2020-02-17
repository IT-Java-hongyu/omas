package com.fosustu.omas.pojo;

public class ChufanMedicineList {
	private String preId;
	private String medicineName;
	private String medicineNum;
	private String medicineUsage;
	
	public String getPreId() {
		return preId;
	}
	public void setPreId(String preId) {
		this.preId = preId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getMedicineNum() {
		return medicineNum;
	}
	public void setMedicineNum(String medicineNum) {
		this.medicineNum = medicineNum;
	}
	public String getMedicineUsage() {
		return medicineUsage;
	}
	public void setMedicineUsage(String medicineUsage) {
		this.medicineUsage = medicineUsage;
	}
	@Override
	public String toString() {
		return "ChufanMedicineList [medicineName=" + medicineName + ", medicineNum=" + medicineNum + ", medicineUsage="
				+ medicineUsage + "]";
	}
	
	
}
