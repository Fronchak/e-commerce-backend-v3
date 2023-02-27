package com.fronchak.e_commerce_v3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronchak.e_commerce_v3.dtos.role.RoleOutputDTO;
import com.fronchak.e_commerce_v3.entities.Role;
import com.fronchak.e_commerce_v3.mappers.RoleMapper;
import com.fronchak.e_commerce_v3.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;
	
	@Autowired
	private RoleMapper mapper;

	@Transactional(readOnly = true)
	public List<RoleOutputDTO> findAll() {
		List<Role> entities = repository.findAll();
		return mapper.convertRolesToRoleOutputDTOs(entities);
	}
}
