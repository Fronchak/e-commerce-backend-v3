package com.fronchak.e_commerce_v3.validations.brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.fronchak.e_commerce_v3.dtos.brand.BrandInsertDTO;
import com.fronchak.e_commerce_v3.dtos.brand.BrandUpdateDTO;
import com.fronchak.e_commerce_v3.entities.Brand;
import com.fronchak.e_commerce_v3.exceptions.FieldMessage;
import com.fronchak.e_commerce_v3.repositories.BrandRepository;

public class BrandUpdateValidator implements ConstraintValidator<BrandUpdateValid, BrandUpdateDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private BrandRepository repository;
	
	@Override
	public boolean isValid(BrandUpdateDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		Map<String, String> uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long id = Long.parseLong(uriVars.get("id"));
		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		
		Brand entity = repository.findByName(dto.getName());
		if (entity != null && !entity.getId().equals(id)) {
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
