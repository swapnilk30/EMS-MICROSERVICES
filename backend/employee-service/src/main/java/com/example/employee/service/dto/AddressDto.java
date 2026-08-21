package com.example.employee.service.dto;

import com.example.employee.service.entity.AddressType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
	
	private Long id;
	
	private Long empId;

	private String city;

	private String state;

	private String zipCode;
	
	private AddressType type;

}
