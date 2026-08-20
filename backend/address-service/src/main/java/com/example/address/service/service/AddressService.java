package com.example.address.service.service;

import java.util.List;

import com.example.address.service.dto.AddressDto;
import com.example.address.service.dto.AddressRequest;

public interface AddressService {

	AddressDto createAddress(AddressDto addressDto);

	List<AddressDto> createAddress(AddressRequest addressRequest);

	AddressDto updateAddress(Long id, AddressDto addressDto);

	List<AddressDto> updateAddress(AddressRequest addressRequest);

	AddressDto patchAddress(Long id, AddressDto addressDto);

	AddressDto getAddressById(Long id);

	List<AddressDto> getAddressByEmpId(Long empId);

	List<AddressDto> getAllAddress();

	void deleteAddress(Long id);

}
