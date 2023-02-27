package com.fronchak.e_commerce_v3.dtos.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v3.entities.Product;
import com.fronchak.e_commerce_v3.factories.ProductMocks;
import com.fronchak.e_commerce_v3.util.ProductAsserts;

@ExtendWith(SpringExtension.class)
public class ProductOutputDTOTest {

	@Test
	public void constructorWithProductShouldCreateCorrectlyObject() {
		Product entity = ProductMocks.mockProduct();
		
		ProductOutputDTO result = new ProductOutputDTO(entity);
		
		ProductAsserts.assertProductOutputDTO(result);
	}
}
