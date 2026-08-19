package com.example.auth_service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_service.dto.JwtTokenResponse;
import com.example.auth_service.dto.UserDto;
import com.example.auth_service.entity.User;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.security.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public UserDto createUser(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser = userRepository.save(user);

		UserDto userDto = UserDto.builder().id(user.getId()).username(user.getUsername()).email(user.getEmail())
				.roles(user.getRoles()).build();

		return userDto;

	}

	public JwtTokenResponse generateToken(String username) {

		String token = jwtService.generateToken(username);

		JwtTokenResponse jwtTokenResponse = JwtTokenResponse.builder().token(token).type("Bearer").validUntil(jwtService.extractExpiration(token).toString()).build();
		
		log.info("JWT token generated successfully for {}", username);
		return jwtTokenResponse;
	}

}
