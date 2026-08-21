package com.example.address.service.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.address.service.client.EmployeeServiceClient;
import com.example.address.service.dto.AddressDto;
import com.example.address.service.dto.AddressRequest;
import com.example.address.service.dto.AddressRequestDto;
import com.example.address.service.dto.EmployeeDto;
import com.example.address.service.entity.Address;
import com.example.address.service.repository.AddressRepository;
import com.example.address.service.service.AddressService;
import com.example.common.lib.exception.ResourceNotFoundException;
import com.example.common.lib.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;
	private final ModelMapper mapper;
	private final EmployeeServiceClient employeeServiceClient; 

	@Override
	public AddressDto createAddress(AddressDto addressDto) {

		// log.info("Creating address for user: {}", addressDto.getUserId());
		Address address = mapper.map(addressDto, Address.class);

		Address savedAddress = addressRepository.save(address);
		// log.info("Address created with ID: {}", addressDto.getId());
		return mapper.map(savedAddress, AddressDto.class);
	}

	@Override
	public AddressDto getAddressById(Long id) {

		log.info("Fetching address with ID: {}", id);

		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Address not found with ID: " + id));

		return mapper.map(address, AddressDto.class);
	}

	@Override
	public List<AddressDto> getAllAddress() {
		List<Address> addresses = addressRepository.findAll();

		List<AddressDto> addressDtos = addresses.stream().map(adr -> mapper.map(adr, AddressDto.class))
				.collect(Collectors.toList());
		return addressDtos;
	}

	@Override
	public List<AddressDto> getAddressByEmpId(Long empId) {
		log.info("Fetching addresses by empId : {}", empId);
		List<Address> addressByEmpId = addressRepository.findAllByEmpId(empId);
		List<AddressDto> addressDtos = addressByEmpId.stream().map(adr -> mapper.map(adr, AddressDto.class))
				.collect(Collectors.toList());
		return addressDtos;
	}

	@Override
	public List<AddressDto> createAddress(AddressRequest addressRequest) {
		
		ApiResponse<EmployeeDto> employeeById = employeeServiceClient.getEmployeeById(addressRequest.getEmpId());
		
		if(employeeById == null || employeeById.getData() == null) {
			throw new ResourceNotFoundException("Employee Not Found with EmpID : "+addressRequest.getEmpId());
		}

		List<Address> addresses = saveOrUpdateAddressRequest(addressRequest);

		List<Address> savedAddresses = addressRepository.saveAll(addresses);

		return savedAddresses.stream().map(adr -> mapper.map(adr, AddressDto.class)).collect(Collectors.toList());
	}

	@Override
	public AddressDto updateAddress(Long id, AddressDto addressDto) {

		Address existingAddress = addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Address Not Found !! "));

		existingAddress.setCity(addressDto.getCity());
		existingAddress.setEmpId(addressDto.getEmpId());
		existingAddress.setState(addressDto.getState());
		existingAddress.setType(addressDto.getType());
		existingAddress.setZipCode(addressDto.getZipCode());

		Address updatedAddress = addressRepository.save(existingAddress);

		return mapper.map(updatedAddress, AddressDto.class);
	}

	@Override
	public void deleteAddress(Long id) {

	}

	@Override
	public AddressDto patchAddress(Long id, AddressDto addressDto) {

		return null;
	}

	@Override
	public List<AddressDto> updateAddress(AddressRequest addressRequest) {

		Long empId = addressRequest.getEmpId();
		List<Address> findAllByEmpId = addressRepository.findAllByEmpId(empId);
		if (findAllByEmpId.isEmpty()) {
			log.info(null);
		}

		List<Address> updatedList = this.saveOrUpdateAddressRequest(addressRequest);

		List<Long> upcomingNonNullIds = updatedList.stream().map(Address::getId).filter(Objects::nonNull).toList();

		List<Long> existingIds = findAllByEmpId.stream().map(Address::getId).toList();

		List<Long> idsToDelete = existingIds.stream().filter(id -> !upcomingNonNullIds.contains(id)).toList();
		
		if (!idsToDelete.isEmpty()) {
			addressRepository.deleteAllById(idsToDelete);
		}

		List<Address> saveAll = addressRepository.saveAll(updatedList);
		
		return saveAll.stream().map(adr -> mapper.map(adr, AddressDto.class)).collect(Collectors.toList());

	}

	private List<Address> saveOrUpdateAddressRequest(AddressRequest addressRequest) {
		List<Address> addresses = new ArrayList<>();

		List<AddressRequestDto> addressRequestDtos = addressRequest.getAddressRequestDtos();
		for (AddressRequestDto addressRequestDto : addressRequestDtos) {
			Address address = new Address();

			address.setEmpId(addressRequest.getEmpId());

			address.setId(addressRequestDto.getId() != null ? addressRequestDto.getId() : null);
			address.setCity(addressRequestDto.getCity());
			address.setState(addressRequestDto.getState());
			address.setZipCode(addressRequestDto.getZipCode());
			address.setType(addressRequestDto.getType());
			addresses.add(address);
		}

		return addresses;
	}

}
