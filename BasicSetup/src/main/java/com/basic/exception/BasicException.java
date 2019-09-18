package com.basic.exception;

public class BasicException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private int code; 
	private String message;
	private String detailMessage;
	public BasicException(int code, String message,String detailMessage) {
		this.code = code;
		this.message = message;
		this.detailMessage=detailMessage;
	}
	
	public BasicException(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getDetailMessage() {
		return detailMessage;
	}


	
	
}
