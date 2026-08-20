package com.example.employee.service.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.example.common.lib.exception.ResourceNotFoundException;
import com.example.common.lib.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		ErrorResponse errorResponse =
				ErrorResponse.builder().success(false).message(ex.getMessage()).errorCode(ex.getErrorCode()).build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}


	@ExceptionHandler(EmployeeAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleEmployeeAlreadyExistsException(
			EmployeeAlreadyExistsException ex) {

		ErrorResponse errorResponse = ErrorResponse.builder()
				.success(false)
				.message(ex.getMessage())
				.errorCode(ex.getErrorCode())
				.build();

		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(errorResponse);
	}
	
	@ExceptionHandler(MissingParameterException.class)
	public ResponseEntity<ErrorResponse> handleMissingParameterException(
			MissingParameterException ex) {

		ErrorResponse errorResponse = ErrorResponse.builder()
				.success(false)
				.message(ex.getMessage())
				.errorCode(ex.getErrorCode())
				.build();

		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(
			MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(
					error.getField(),
					error.getDefaultMessage()
			);
		});

		ErrorResponse response = ErrorResponse.builder()
				.success(false)
				.message("Validation failed")
				.errorCode("VALIDATION_ERROR")
				.errors(errors)
				.build();

		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(response);
	}

}
