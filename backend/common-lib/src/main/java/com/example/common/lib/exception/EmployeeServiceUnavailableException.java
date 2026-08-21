package com.example.common.lib.exception;

public class EmployeeServiceUnavailableException extends RuntimeException {

	private final String errorCode;

	public EmployeeServiceUnavailableException(String message) {
		super(message);
		this.errorCode = "EMPLOYEE_SERVICE_UNAVAILABLE";
	}

	public String getErrorCode() {
		return errorCode;
	}

}
