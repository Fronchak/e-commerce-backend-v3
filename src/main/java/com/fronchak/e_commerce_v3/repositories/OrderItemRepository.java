package com.fronchak.e_commerce_v3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fronchak.e_commerce_v3.entities.OrderItem;
import com.fronchak.e_commerce_v3.entities.pks.OrderItemPK;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
