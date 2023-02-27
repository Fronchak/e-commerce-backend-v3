package com.fronchak.e_commerce_v3.dtos.product;

import java.util.ArrayList;
import java.util.List;

import com.fronchak.e_commerce_v3.dtos.assessment.AssessmentOutputDTO;
import com.fronchak.e_commerce_v3.dtos.brand.BrandOutputDTO;
import com.fronchak.e_commerce_v3.dtos.category.CategoryNameOutputDTO;
import com.fronchak.e_commerce_v3.entities.Product;

public class ProductOutputDTO extends ProductSimpleOutputDTO {

	private static final long serialVersionUID = 1L;

	private String description;
	private boolean inStock;
	private BrandOutputDTO brand;
	private List<CategoryNameOutputDTO> categories = new ArrayList<>();
	private List<AssessmentOutputDTO> assessments = new ArrayList<>();
	
	public ProductOutputDTO() {}
	
	public ProductOutputDTO(Product product) {
		super(product);
		description = product.getDescription();
		inStock = product.inStock();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public BrandOutputDTO getBrand() {
		return brand;
	}

	public void setBrand(BrandOutputDTO brand) {
		this.brand = brand;
	}

	public List<CategoryNameOutputDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryNameOutputDTO> categories) {
		this.categories = categories;
	}

	public List<AssessmentOutputDTO> getAssessments() {
		return assessments;
	}

	public void setAssessments(List<AssessmentOutputDTO> assessments) {
		this.assessments = assessments;
	}
}
