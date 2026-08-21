package com.example.employee.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignClientConfig {
	
	
	
	@Bean
    public ErrorDecoder errorDecoder(ObjectMapper objectMapper ) {
        return new CustomFeignErrorDecoder(objectMapper);
    }

}
