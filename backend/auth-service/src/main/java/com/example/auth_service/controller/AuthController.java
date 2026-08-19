package com.example.auth_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.dto.AuthRequest;
import com.example.auth_service.dto.AuthResponse;
import com.example.auth_service.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationService authenticationService;

	
	/*
	 * @PostMapping("/login") public ResponseEntity<AuthResponse>
	 * authenticate(@Valid @RequestBody AuthRequest request) { return
	 * ResponseEntity.ok(authenticationService.authenticate(request)); }
	 */
	 
	/*
	 * @PostMapping("/register") public ResponseEntity<AuthResponse>
	 * register(@Valid @RequestBody RegisterRequest request) { return
	 * ResponseEntity.ok(authenticationService.register(request)); }
	 */

}
