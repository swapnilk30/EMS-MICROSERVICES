package com.example.address.service.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.common.lib.exception.EmployeeServiceUnavailableException;
import com.example.common.lib.exception.ResourceNotFoundException;
import com.example.common.lib.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		ErrorResponse errorResponse = ErrorResponse.builder().success(false).message(ex.getMessage())
				.errorCode(ex.getErrorCode()).build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {

		String message = "Invalid request body";

		if (ex.getMessage() != null && ex.getMessage().contains("AddressType")) {
			message = "Invalid addressType. Allowed values are: HOME, WORK, PERMANENT, TEMPORARY";
		}

		ErrorResponse response = ErrorResponse.builder().success(false).message(message)
				.errorCode("INVALID_REQUEST_BODY").build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		ErrorResponse response = ErrorResponse.builder().success(false).message("Validation failed")
				.errorCode("VALIDATION_ERROR").errors(errors).build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {

		ErrorResponse response = ErrorResponse.builder().success(false).message("Database constraint violation")
				.errorCode("DATA_INTEGRITY_ERROR").build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(EmployeeServiceUnavailableException.class)
	public ResponseEntity<ErrorResponse> handleEmployeeServiceUnavailable(EmployeeServiceUnavailableException ex) {

		ErrorResponse response = ErrorResponse.builder().success(false).message(ex.getMessage())
				.errorCode(ex.getErrorCode()).build();

		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
	}
}
