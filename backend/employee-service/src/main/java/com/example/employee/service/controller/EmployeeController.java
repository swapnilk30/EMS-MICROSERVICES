package com.example.employee.service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.lib.response.ApiResponse;
import com.example.employee.service.client.AddressServiceClient;
import com.example.employee.service.dto.EmployeeDto;
import com.example.employee.service.exception.MissingParameterException;
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

    @Value("${application.message}")
    private String message;

    @GetMapping("/message")
    public String getMessage() {
        return message;
    }
    

	
	@PostMapping
	public ResponseEntity<ApiResponse<EmployeeDto>> createEmployee(@Valid @RequestBody EmployeeDto employeeDto){
		
		EmployeeDto response = employeeService.createEmployee(employeeDto);

		ApiResponse<EmployeeDto> apiResponse =
				ApiResponse.success("Employee created successfully", response);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(apiResponse);
	}

	//update employee

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeDto>> getEmployeeById(@PathVariable Long id) {
		EmployeeDto response = employeeService.getEmployeeById(id);

		return ResponseEntity.ok(
				ApiResponse.success(
						"Employee fetched successfully",
						response
				)
		);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<EmployeeDto>>> getAllEmployees() {
		List<EmployeeDto> responses = employeeService.getAllEmployees();
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(ApiResponse.success(
						"Employees fetched successfully",
						responses
				));

	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
		return ResponseEntity.ok(
				ApiResponse.success(
						"Employee deleted successfully",
						null
				)
		);
    }
	
	
	@GetMapping("/get-by-empcode-empname")
	public ResponseEntity<ApiResponse<EmployeeDto>> getByEmpCodeAndCompanyName(
			@RequestParam(required = false) String empCode,
			@RequestParam(required = false) String companyName){
		
		List<String> missingParams = new ArrayList<>();
		
		if(empCode == null || empCode.trim().isEmpty()) {
			missingParams.add("empCode");	
		}
		if(companyName == null || companyName.trim().isEmpty()) {
			missingParams.add("companyName");
		}
		
		if(!missingParams.isEmpty()) {
			String finalMessage = missingParams.stream().collect(Collectors.joining(","));
			throw new MissingParameterException("Please provide : "+finalMessage);
		}
		
		EmployeeDto employeeDto =  employeeService.getByEmpCodeAndCompanyName(empCode,companyName);
		
		ApiResponse<EmployeeDto> response = ApiResponse.success("Employee fetched successfully", employeeDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
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
