package com.example.employee.service.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.common.lib.exception.ResourceNotFoundException;
import com.example.employee.service.exception.EmployeeAlreadyExistsException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.employee.service.client.AddressServiceClient;
import com.example.employee.service.dto.EmployeeDto;
import com.example.employee.service.entity.Employee;
import com.example.employee.service.repository.EmployeeRepository;
import com.example.employee.service.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final ModelMapper mapper;

	@Override
	@Transactional
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {

		log.info("Creating new employee");

		String email = employeeDto.getEmail().trim().toLowerCase();
		employeeDto.setEmail(email);

		if (employeeRepository.existsByEmail(employeeDto.getEmail())) {
			throw new EmployeeAlreadyExistsException(
					"Employee already exists with email: " + employeeDto.getEmail()
			);
		}

		Employee employee = mapper.map(employeeDto, Employee.class);

		Employee savedEmployee = employeeRepository.save(employee);


		log.info("Employee created successfully with ID: {}", savedEmployee.getEmpId());

		return mapper.map(savedEmployee, EmployeeDto.class);
	}

	@Override
	public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {

		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));

		existingEmployee.setFirstName(employeeDto.getFirstName());
		existingEmployee.setLastName(employeeDto.getLastName());
		existingEmployee.setEmail(employeeDto.getEmail());

		Employee updatedEmployee = employeeRepository.save(existingEmployee);

		return mapper.map(updatedEmployee, EmployeeDto.class);
	}

	@Override
	public EmployeeDto getEmployeeById(Long id) {
		log.info("Fetching employee with ID: {}", id);

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
	
		return mapper.map(employee, EmployeeDto.class);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		log.info("Fetching all employees");
		
		List<Employee> employees = employeeRepository.findAll();
		
		List<EmployeeDto> employeeDtos = employees.stream().map(emp -> mapper.map(emp, EmployeeDto.class)).collect(Collectors.toList());
		
		return employeeDtos;
	}

	@Override
	public void deleteEmployee(Long id) {
		log.info("Deleting employee with ID: {}", id);

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
		employeeRepository.delete(employee);

	}

	@Override
	public void hardDeleteEmployee(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public EmployeeDto restoreEmployee(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeDto patchEmployee(Long id, Map<String, Object> updates) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	

}
