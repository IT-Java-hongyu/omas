package com.fosustu.omas.pojo;

public class CardInform {

	private String patientId;
	private String cardId;
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	@Override
	public String toString() {
		return "CardInform [patientId=" + patientId + ", cardId=" + cardId + "]";
	}
}
