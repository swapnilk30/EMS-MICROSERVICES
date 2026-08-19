package com.example.employee.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.lib.response.ApiResponse;
import com.example.employee.service.client.AddressServiceClient;
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
	private final AddressServiceClient addressServiceClient;
	
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

	@GetMapping("/address-service/health")
	public Map<String,Object> getAdrressHealth() {
		Map<String,Object> health = addressServiceClient.getHealth();
		System.out.println(health);
		return health;
	}
	
	@GetMapping("/address-service/ping")
	public void getAddressPing(){
		String ping = addressServiceClient.ping();
		System.out.println(ping);
	}
	
	
	@GetMapping("/hello")
	public ApiResponse<String> hello() {

	    return new ApiResponse<>(
	            true,
	            "Auth Service is running",
	            "Hello from Employee Service"
	    );
	}

}
