package com.example.address.service.dto;

import java.util.List;

import com.example.address.service.entity.AddressType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
	
    private Long empId;
    
    private List<AddressRequestDto> addressRequestDtos;

}
