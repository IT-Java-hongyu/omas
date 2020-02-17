package com.fosustu.omas.exception;
//自定义异常
public class UserException extends Exception {
	//异常信息
	private String message;

	
	public UserException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
