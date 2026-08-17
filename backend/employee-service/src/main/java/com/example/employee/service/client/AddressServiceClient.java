package com.example.employee.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.employee.service.dto.ApiResponse;

@FeignClient()
public interface AddressServiceClient {

	// ============================================
	// HEALTH & MONITORING
	// ============================================

	/**
	 * Check address service health
	 */
	@GetMapping("/actuator/health")
	ApiResponse<String> getHealth();

	/**
	 * Check if address service is available
	 */
	@GetMapping("/api/addresses/ping")
	ApiResponse<String> ping();

}
