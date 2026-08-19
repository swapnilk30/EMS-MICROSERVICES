package com.example.common.lib.exception;

public class BadRequestException extends RuntimeException{

	public BadRequestException(String message) {
		super(message);
	}
}
