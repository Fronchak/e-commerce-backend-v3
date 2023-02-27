package com.fronchak.e_commerce_v3.dtos.role;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v3.entities.Role;
import com.fronchak.e_commerce_v3.factories.RoleMocks;
import com.fronchak.e_commerce_v3.util.RoleAsserts;

@ExtendWith(SpringExtension.class)
public class RoleOutputDTOTest {

	@Test
	public void constructorWithRoleShouldCreateCorrectlyObject() {
		Role entity = RoleMocks.mockRole();
		
		RoleOutputDTO result = new RoleOutputDTO(entity);
		
		RoleAsserts.assertRoleOutputDTO_0(result);
	}
}
