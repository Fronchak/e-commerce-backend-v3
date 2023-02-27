package com.fronchak.e_commerce_v3.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sell")
public class Sell extends Order {

	@ManyToOne
	@JoinColumn(name = "id_address")
	private Address deliveryAddress;

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
}
