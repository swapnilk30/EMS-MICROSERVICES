package com.example.address.service.dto;

import java.util.List;

import com.example.address.service.entity.AddressType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDto {

	private Long id;

	private String city;

	private String state;

	private String zipCode;

	private AddressType type;

}
