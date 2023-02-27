package com.fronchak.e_commerce_v3.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fronchak.e_commerce_v3.dtos.brand.BrandInsertDTO;
import com.fronchak.e_commerce_v3.dtos.brand.BrandOutputDTO;
import com.fronchak.e_commerce_v3.dtos.brand.BrandUpdateDTO;
import com.fronchak.e_commerce_v3.services.BrandService;

@RestController
@RequestMapping(value = "/brands")
public class BrandController {

	@Autowired
	private BrandService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<BrandOutputDTO> findById(@PathVariable Long id) {
		BrandOutputDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping
	public ResponseEntity<List<BrandOutputDTO>> findAll() {
		List<BrandOutputDTO> dtos = service.findAll();
		return ResponseEntity.ok().body(dtos);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<BrandOutputDTO> save(@Valid @RequestBody BrandInsertDTO inputDTO) {
		BrandOutputDTO outputDTO = service.save(inputDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(outputDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(outputDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<BrandOutputDTO> update(@Valid @RequestBody BrandUpdateDTO inputDTO, @PathVariable Long id) {
		BrandOutputDTO outputDTO = service.update(inputDTO, id);
		return ResponseEntity.ok().body(outputDTO);
	}
}
