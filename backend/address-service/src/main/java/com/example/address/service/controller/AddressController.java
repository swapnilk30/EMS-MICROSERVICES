package com.example.address.service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.address.service.dto.AddressDto;
import com.example.address.service.service.AddressService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {
	
	private final AddressService addressService;
	
    @PostMapping
    public ResponseEntity<?> createAddress(@Valid @RequestBody AddressDto addressDto) {
        AddressDto response = addressService.createAddress(addressDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getAddressById(@PathVariable Long id) {
        AddressDto response = addressService.getAddressById(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    public ResponseEntity<?> getAllAddress(){
    	
    	List<AddressDto> allAddress = addressService.getAllAddress();
    	
    	return ResponseEntity.ok(allAddress);
    	
    }
    
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
    	throw new RuntimeException("Exception Occur");
       // return ResponseEntity.ok("Address service is running");
    }


}
