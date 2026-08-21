package com.example.common.lib.exception;

public class AddressServiceUnavailableException extends RuntimeException{

	private final String errorCode;

	public AddressServiceUnavailableException(String message) {
		super(message);
		this.errorCode = "ADDRESS_SERVICE_UNAVAILABLE";
	}

	public String getErrorCode() {
		return errorCode;
	}

}
