package com.example.address.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.address.service.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
