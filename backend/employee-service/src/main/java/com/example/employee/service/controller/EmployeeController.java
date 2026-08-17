package com.example.employee.service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.service.dto.EmployeeDto;
import com.example.employee.service.service.EmployeeService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

	private final EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDto employeeDto){
		
		log.info("Employee DTO: {}", employeeDto);
		
		EmployeeDto response = employeeService.createEmployee(employeeDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
		EmployeeDto response = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<?> getAllEmployees() {
		List<EmployeeDto> responses = employeeService.getAllEmployees();
		return ResponseEntity.ok(responses);
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
