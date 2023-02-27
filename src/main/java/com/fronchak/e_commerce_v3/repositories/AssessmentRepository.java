package com.fronchak.e_commerce_v3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fronchak.e_commerce_v3.entities.Assessment;
import com.fronchak.e_commerce_v3.entities.pks.AssessmentPK;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, AssessmentPK> {

	//List<Assessment> findAllByProduct(Product product);
}
