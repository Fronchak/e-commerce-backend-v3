package com.fronchak.e_commerce_v3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fronchak.e_commerce_v3.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

	Brand findByName(String name);
}
