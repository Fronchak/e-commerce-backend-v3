package com.fronchak.e_commerce_v3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fronchak.e_commerce_v3.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
