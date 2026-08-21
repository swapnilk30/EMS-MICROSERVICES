package com.example.employee.service.service;

import java.util.List;
import java.util.Map;

import com.example.employee.service.dto.EmployeeDetailsDto;
import com.example.employee.service.dto.EmployeeDto;

public interface EmployeeService {

	EmployeeDto createEmployee(EmployeeDto employeeDto);

	EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto);

	EmployeeDto getEmployeeById(Long id);

	List<EmployeeDto> getAllEmployees();

	void deleteEmployee(Long id);
	
	void hardDeleteEmployee(Long id);
	
	EmployeeDto restoreEmployee(Long id);

	EmployeeDto patchEmployee(Long id, Map<String, Object> updates);
	
	EmployeeDto getByEmpCodeAndCompanyName(String empCode,String companyName);
	
	EmployeeDetailsDto getEmployeeDetails(Long empId);
}
