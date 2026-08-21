package com.example.employee.service.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.example.common.lib.response.ApiResponse;
import com.example.employee.service.dto.AddressDto;

@FeignClient(name = "address-service", url = "${address.service.url}")
public interface AddressServiceClient {

	@GetMapping("/api/addresses/get-address/{emp_id}")
	ApiResponse<List<AddressDto>> getAddressByEmpId(@PathVariable("emp_id") Long emp_id);
	
	
	

	// ============================================
	// HEALTH & MONITORING
	// ============================================

	/**
	 * Check address service health
	 */
	@GetMapping("/actuator/health")
	// ApiResponse<String> getHealth();
	Map<String, Object> getHealth();

	/**
	 * Check if address service is available
	 */
	@GetMapping("/api/addresses/ping")
	String ping();
	// ApiResponse<String> ping();

}
