package com.fronchak.e_commerce_v3.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fronchak.e_commerce_v3.entities.pks.OrderItemPK;

@Entity
@Table(name = "order_item")
public class OrderItem {

	@EmbeddedId
	private OrderItemPK id;
	
	private Double price;
	private Integer quantity;
	
	public OrderItem() {};
	
	public OrderItem(Order order, Product product) {
		setId(order, product);
	}
	
	public void setId(Order order, Product product) {
		id.setOrder(order);
		id.setProduct(product);
	}
	
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}
}
