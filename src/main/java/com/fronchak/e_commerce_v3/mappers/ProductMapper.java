package com.fronchak.e_commerce_v3.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fronchak.e_commerce_v3.dtos.product.ProductInputDTO;
import com.fronchak.e_commerce_v3.dtos.product.ProductOutputDTO;
import com.fronchak.e_commerce_v3.dtos.product.ProductSimpleOutputDTO;
import com.fronchak.e_commerce_v3.entities.Product;

@Service
public class ProductMapper {

	@Autowired
	private BrandMapper brandMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private AssessmentMapper assessmentMapper;
	
	public ProductOutputDTO convertProductToProductOutputDTO(Product entity) {
		ProductOutputDTO dto = new ProductOutputDTO(entity);
		dto.setBrand(brandMapper.convertBrandToBrandOutputDTO(entity.getBrand()));
		dto.setCategories(categoryMapper.convertCategoriesToCategoryNameOutputDTOs(entity.getCategories()));
		dto.setAssessments(assessmentMapper.convertAssessmentsToAssessmentOutputDTOs(entity.getAssessments()));
		return dto;
	}
	
	public Page<ProductSimpleOutputDTO> convertProductsToProductSimpleOutputDTOs(Page<Product> entities) {
		return entities.map((entity) -> convertProductToProductSimpleOutputDTO(entity));
	}
	
	public ProductSimpleOutputDTO convertProductToProductSimpleOutputDTO(Product entity) {
		return new ProductSimpleOutputDTO(entity);
	}
	
	public void copyProductInputDTOToProduct(ProductInputDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setImgUrl(dto.getImgUrl());
		entity.setPrice(dto.getPrice());
	}
}
