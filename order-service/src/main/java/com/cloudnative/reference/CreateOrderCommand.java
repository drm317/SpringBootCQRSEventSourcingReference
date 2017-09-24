package com.cloudnative.reference;

import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

public class CreateOrderCommand {

	public CreateOrderCommand() {}
	public CreateOrderCommand(String id, String description) {
		this.description = description;
		this.id = id;
	}
	
	@AggregateIdentifier
	private String id;

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

	private String description;
}
