package com.fronchak.e_commerce_v3.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fronchak.e_commerce_v3.dtos.brand.BrandInputDTO;
import com.fronchak.e_commerce_v3.dtos.brand.BrandOutputDTO;
import com.fronchak.e_commerce_v3.entities.Brand;

@Service
public class BrandMapper {

	public BrandOutputDTO convertBrandToBrandOutputDTO(Brand entity) {
		return new BrandOutputDTO(entity);
	}
	
	public List<BrandOutputDTO> convertBrandsToBrandOutputDTOs(List<Brand> entities) {
		return entities.stream()
				.map((entity) -> convertBrandToBrandOutputDTO(entity))
				.collect(Collectors.toList());
	}
	
	public void copyBrandInputDTOToBrand(BrandInputDTO dto, Brand entity) {
		entity.setName(dto.getName());
		entity.setImgUrl(dto.getImgUrl());
	}
}
