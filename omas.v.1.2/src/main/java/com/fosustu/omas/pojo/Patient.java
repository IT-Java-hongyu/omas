package com.fosustu.omas.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Patient {
    
	/*
	 * pId  这种命名不规范：以p单个字母小写开头，接着大写字母会造属性无法转JSON
	 * 
	 */
			
	
	private String patientId;
    private String patientName;


    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private String patientBirthday;

    private Integer patientSex;

    private String patientAddress;

    private Integer patientMarriage;

    private String patientPhone;

    private String patientEmail;

    private String patientPor;

    private String cardId;

    private Integer guahaoNum;  //挂号码
    
	public Integer getGuahaoNum() {
		return guahaoNum;
	}

	public void setGuahaoNum(Integer guahaoNum) {
		this.guahaoNum = guahaoNum;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	

	public String getPatientBirthday() {
		return patientBirthday;
	}

	public void setPatientBirthday(String patientBirthday) {
		this.patientBirthday = patientBirthday;
	}

	public Integer getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(Integer patientSex) {
		this.patientSex = patientSex;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public Integer getPatientMarriage() {
		return patientMarriage;
	}

	public void setPatientMarriage(Integer patientMarriage) {
		this.patientMarriage = patientMarriage;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getPatientPor() {
		return patientPor;
	}

	public void setPatientPor(String patientPor) {
		this.patientPor = patientPor;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientName=" + patientName + ", patientBirthday="
				+ patientBirthday + ", patientSex=" + patientSex + ", patientAddress=" + patientAddress
				+ ", patientMarriage=" + patientMarriage + ", patientPhone=" + patientPhone + ", patientEmail="
				+ patientEmail + ", patientPor=" + patientPor + ", cardId=" + cardId + ", guahaoNum=" + guahaoNum + "]";
	}

    

    
    
    
}