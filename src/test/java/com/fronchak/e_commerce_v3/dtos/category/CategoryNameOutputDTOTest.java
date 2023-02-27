package com.fronchak.e_commerce_v3.dtos.category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v3.entities.Category;
import com.fronchak.e_commerce_v3.factories.CategoryMocks;
import com.fronchak.e_commerce_v3.util.CategoryAsserts;

@ExtendWith(SpringExtension.class)
public class CategoryNameOutputDTOTest {

	@Test
	public void constructorWithCategoryShouldCreateCorrectlyObject() {
		Category entity = CategoryMocks.mockCategory();
		
		CategoryNameOutputDTO result = new CategoryNameOutputDTO(entity);
		
		CategoryAsserts.assertCategoryNameOutputDTO_0(result);
	}
}
