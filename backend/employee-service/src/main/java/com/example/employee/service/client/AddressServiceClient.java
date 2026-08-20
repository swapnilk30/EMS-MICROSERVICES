package com.example.employee.service.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;



@FeignClient(name = "address-service",url = "${address.service.url}")
public interface AddressServiceClient {
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// ============================================
	// HEALTH & MONITORING
	// ============================================

	/**
	 * Check address service health
	 */
	@GetMapping("/actuator/health")
	//ApiResponse<String> getHealth();
	Map<String, Object> getHealth();

	/**
	 * Check if address service is available
	 */
	@GetMapping("/api/addresses/ping")
	String ping();
	//ApiResponse<String> ping();

}
