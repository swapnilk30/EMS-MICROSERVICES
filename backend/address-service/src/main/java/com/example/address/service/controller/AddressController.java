package com.example.address.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.address.service.dto.AddressDto;
import com.example.address.service.dto.AddressRequest;
import com.example.address.service.service.AddressService;
import com.example.common.lib.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

	private final AddressService addressService;

	@Value("${application.message}")
	private String message;

	@GetMapping("/message")
	public String getMessage() {
		return message;
	}

	@PostMapping
	public ResponseEntity<?> createAddress(@Valid @RequestBody AddressDto addressDto) {
		AddressDto response = addressService.createAddress(addressDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PostMapping("/save")
	public ResponseEntity<ApiResponse<List<AddressDto>>> createAddress(@RequestBody AddressRequest addressRequest){
		
		List<AddressDto> createdAddress = addressService.createAddress(addressRequest);
		
		 ApiResponse<List<AddressDto>> response = ApiResponse.success("Addresses saved succefully !!", createdAddress);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<AddressDto>> updateAddress(@PathVariable Long id,
			@RequestBody AddressDto addressDto) {
		AddressDto updatedAddress = addressService.updateAddress(id, addressDto);

		ApiResponse<AddressDto> response = ApiResponse.success("Address Updated Succefully !!", updatedAddress);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ApiResponse<List<AddressDto>>> updateAddress(@RequestBody AddressRequest addressRequest) {
		List<AddressDto> updatedAddress = addressService.updateAddress(addressRequest);

		ApiResponse<List<AddressDto>> response = ApiResponse.success("Address Updated Succefully !!", updatedAddress);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAddressById(@PathVariable Long id) {
		AddressDto response = addressService.getAddressById(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/get-address/{emp_id}")
	public ResponseEntity<ApiResponse<List<AddressDto>>> getAddressByEmpId(Long emp_id) {

		List<AddressDto> addressByEmpId = addressService.getAddressByEmpId(emp_id);

		ApiResponse<List<AddressDto>> response = ApiResponse.success("fetch address by emp id : ", addressByEmpId);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<AddressDto>>> getAllAddress() {

		List<AddressDto> allAddress = addressService.getAllAddress();

		ApiResponse<List<AddressDto>> response = ApiResponse.success("Addresses fetch succefully", allAddress);

		return ResponseEntity.ok(response);

	}

	@GetMapping("/ping")
	public ResponseEntity<String> ping() {
		throw new RuntimeException("Exception Occur");
		// return ResponseEntity.ok("Address service is running");
	}

}
