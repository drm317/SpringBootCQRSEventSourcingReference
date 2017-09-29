package com.cloudnative.reference.events;

public class OrderCreatedEvent {

	private String orderId;
	
	private String description;

	
	public OrderCreatedEvent(String orderId, String description) {
		super();
		this.orderId = orderId;
		this.description = description;
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
