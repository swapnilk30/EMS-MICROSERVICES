package com.example.address.service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.address.service.dto.AddressDto;
import com.example.address.service.entity.Address;
import com.example.address.service.repository.AddressRepository;
import com.example.address.service.service.AddressService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService{
	
	private final AddressRepository addressRepository;
	private final ModelMapper mapper;

	@Override
	public AddressDto createAddress(AddressDto addressDto) {
		//log.info("Creating address for user: {}", addressDto.getUserId());
		Address address = mapper.map(addressDto, Address.class);
		
		Address savedAddress = addressRepository.save(address);
		//log.info("Address created with ID: {}", addressDto.getId());
		return mapper.map(savedAddress, AddressDto.class);
	}

	@Override
	public AddressDto getAddressById(Long id) {
		
		log.info("Fetching address with ID: {}", id);
		
		Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with ID: " + id));
		
		return mapper.map(address, AddressDto.class);
	}

	@Override
	public List<AddressDto> getAllAddress() {
		List<Address> addresses = addressRepository.findAll();
		
		List<AddressDto> addressDtos = addresses.stream().map(adr -> mapper.map(adr, AddressDto.class)).collect(Collectors.toList());
		return addressDtos;
	}

}
