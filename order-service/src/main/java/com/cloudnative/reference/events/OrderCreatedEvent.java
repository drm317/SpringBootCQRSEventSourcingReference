package com.cloudnative.reference.events;

public class OrderCreatedEvent {

	private String id;
	
	private String description;

	
	public OrderCreatedEvent(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
