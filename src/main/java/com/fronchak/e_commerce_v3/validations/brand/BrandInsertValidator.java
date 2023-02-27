package com.fronchak.e_commerce_v3.validations.brand;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.fronchak.e_commerce_v3.dtos.brand.BrandInsertDTO;
import com.fronchak.e_commerce_v3.entities.Brand;
import com.fronchak.e_commerce_v3.exceptions.FieldMessage;
import com.fronchak.e_commerce_v3.repositories.BrandRepository;

public class BrandInsertValidator implements ConstraintValidator<BrandInsertValid, BrandInsertDTO> {

	@Autowired
	private BrandRepository repository;
	
	@Override
	public boolean isValid(BrandInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		
		Brand entity = repository.findByName(dto.getName());
		if (entity != null) {
			list.add(new FieldMessage("name", "This brand is already registered"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
