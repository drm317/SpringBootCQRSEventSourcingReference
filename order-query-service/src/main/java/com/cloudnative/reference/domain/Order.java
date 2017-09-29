package com.cloudnative.reference.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Order {

	@Id
    private String orderId;

    private String description;

	public Order(String orderId, String description) {
		super();
		this.orderId = orderId;
		this.description = description;
	}

	public Order() {
	}


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    
    
}
