package com.example.common.lib.exception;

public class ResourceNotFoundException extends RuntimeException {

	private final String errorCode;

	public ResourceNotFoundException(String message) {
		super(message);
		this.errorCode = "RESOURCE_NOT_FOUND";
	}

	public String getErrorCode() {
		return errorCode;
	}

}
