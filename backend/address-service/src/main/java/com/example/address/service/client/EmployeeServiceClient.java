package com.example.address.service.client;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.address.service.config.FeignClientConfig;
import com.example.address.service.dto.EmployeeDto;
import com.example.common.lib.response.ApiResponse;


@FeignClient(name = "employee-service",configuration = FeignClientConfig.class)//,url = "${employee.service.url}")
public interface EmployeeServiceClient {
	

	@GetMapping("/api/employees/{id}")
	ApiResponse<EmployeeDto> getEmployeeById(@PathVariable("id") Long id) ;
		


}
