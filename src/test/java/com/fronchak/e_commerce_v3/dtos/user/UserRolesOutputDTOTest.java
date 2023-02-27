package com.fronchak.e_commerce_v3.dtos.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v3.entities.User;
import com.fronchak.e_commerce_v3.factories.UserMocks;
import com.fronchak.e_commerce_v3.util.UserAsserts;

@ExtendWith(SpringExtension.class)
public class UserRolesOutputDTOTest {

	@Test
	public void constructorWithUserShouldCreateCorrectlyObject() {
		User entity = UserMocks.mockUser();
		
		UserRolesOutputDTO result = new UserRolesOutputDTO(entity);
		
		UserAsserts.assertUserOutputDTO_0(result);
	}
}
