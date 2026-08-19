package com.example.auth_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.dto.AuthRequest;
import com.example.auth_service.dto.JwtTokenResponse;
import com.example.auth_service.dto.UserDto;
import com.example.auth_service.entity.User;
import com.example.auth_service.exception.InvalidCredentialsException;
import com.example.auth_service.service.AuthenticationService;
import com.example.auth_service.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final AuthenticationManager authenticationManager;

	@PostMapping("/create-user")
	public ResponseEntity<UserDto> createUser(@RequestBody User user) {

		UserDto createUser = userService.createUser(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
	}

	@PostMapping("/generate-token")
	public ResponseEntity<JwtTokenResponse> generateToken(@RequestBody AuthRequest authRequest) {
		
		
		try {
			
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			
			if (authentication.isAuthenticated()) {
				JwtTokenResponse jwtTokenResponse = userService.generateToken(authRequest.getUsername());
				return new ResponseEntity<JwtTokenResponse>(jwtTokenResponse, HttpStatus.OK);
			} else {
				throw new InvalidCredentialsException("Invalid username or password");
			}
			
		} catch (Exception e) {
			throw new InvalidCredentialsException("Invalid username or password");
		}

	}

}
