package com.fronchak.e_commerce_v3.mappers;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fronchak.e_commerce_v3.dtos.role.RoleOutputDTO;
import com.fronchak.e_commerce_v3.entities.Role;
import com.fronchak.e_commerce_v3.factories.RoleMocks;
import com.fronchak.e_commerce_v3.util.RoleAsserts;

@ExtendWith(SpringExtension.class)
public class RoleMapperTest {

	private RoleMapper mapper;
	
	@BeforeEach
	void setUp() {
		mapper = new RoleMapper();
	}
	
	@Test
	public void convertRoleToRoleOutputDTOShouldConvertCorrectly() {
		Role entity = RoleMocks.mockRole();
		
		RoleOutputDTO result = mapper.convertRoleToRoleOutputDTO(entity);
		
		RoleAsserts.assertRoleOutputDTO_0(result);
	}
	
	@Test
	public void convertRolesToRoleOutputDTOsShouldConvertCorrectly() {
		Set<Role> entities = RoleMocks.mockRoles();
		
		List<RoleOutputDTO> result = mapper.convertRolesToRoleOutputDTOs(entities);
		
		RoleAsserts.assertRoleOutputDTOs(result);
	}
}
