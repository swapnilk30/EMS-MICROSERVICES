package com.example.employee.service.dto;


import jakarta.validation.constraints.NotBlank;
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

	@NotBlank(message = "First name is required")
	private String firstName;

	private String lastName;
	
	private String email;

	private String empCode;

	private String companyName;

}
