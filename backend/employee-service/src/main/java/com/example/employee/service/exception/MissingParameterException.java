package com.example.employee.service.exception;

public class MissingParameterException extends RuntimeException{

	 private final String errorCode;
	
	public MissingParameterException(String message) {
		super(message);
		this.errorCode = "MISSING_PARAMETERS";
	}

	public String getErrorCode() {
		return errorCode;
	}
	
	
}
