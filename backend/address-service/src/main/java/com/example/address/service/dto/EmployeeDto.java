package com.example.address.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

	private Long empId;

	private String firstName;

	private String lastName;
	
	private String email;

	private String empCode;

	private String companyName;

}
