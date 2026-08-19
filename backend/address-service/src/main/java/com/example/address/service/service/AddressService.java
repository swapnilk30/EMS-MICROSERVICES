package com.example.address.service.service;

import java.util.List;

import com.example.address.service.dto.AddressDto;

public interface AddressService {
	
	
	AddressDto createAddress(AddressDto addressDto);
	
	AddressDto getAddressById(Long id);
	
	List<AddressDto> getAddressByEmpId(Long empId);
	
	List<AddressDto> getAllAddress();

}
