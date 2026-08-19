package com.example.address.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.address.service.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
	
	List<Address> findAllByEmpId(Long empId);

}
