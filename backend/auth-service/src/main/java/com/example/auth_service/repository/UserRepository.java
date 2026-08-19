package com.example.auth_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth_service.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	
	boolean existsByUsername(String userName);
	
	boolean existsByEmail(String email);
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);

}
