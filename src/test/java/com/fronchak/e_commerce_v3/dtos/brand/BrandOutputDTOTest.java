package com.fronchak.e_commerce_v3.dtos.brand;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v3.entities.Brand;
import com.fronchak.e_commerce_v3.factories.BrandMocks;
import com.fronchak.e_commerce_v3.util.BrandAsserts;

@ExtendWith(SpringExtension.class)
public class BrandOutputDTOTest {

	@Test
	public void constructorWithBrandShouldCreateCorrectlyObject() {
		Brand entity = BrandMocks.mockBrand();
		
		BrandOutputDTO result = new BrandOutputDTO(entity);
		
		BrandAsserts.assertBrandOutputDTO_0(result);
	}
}
