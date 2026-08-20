package com.example.employee.service.exception;

public class EmployeeAlreadyExistsException extends RuntimeException {

    private final String errorCode;

    public EmployeeAlreadyExistsException(String message) {
        super(message);
        this.errorCode = "EMPLOYEE_ALREADY_EXISTS";
    }

    public String getErrorCode() {
        return errorCode;
    }
}
