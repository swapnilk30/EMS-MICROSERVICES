package com.example.auth_service.dto;

import com.example.auth_service.entity.Role;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private String id;
	private String username;
	private String email;
	//private Role role;
	private String roles;

}
