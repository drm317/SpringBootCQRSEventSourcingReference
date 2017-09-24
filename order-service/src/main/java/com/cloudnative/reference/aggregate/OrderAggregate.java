package com.cloudnative.reference.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.springframework.util.Assert;

import com.cloudnative.reference.CreateOrderCommand;
import com.cloudnative.reference.OrderCreatedEvent;


@SuppressWarnings("rawtypes")
public class OrderAggregate extends AbstractAnnotatedAggregateRoot{

	private static final long serialVersionUID = 5108759807090437372L;
	
	@AggregateIdentifier
	private String orderId;

	public OrderAggregate() {}

	@CommandHandler
	public OrderAggregate(CreateOrderCommand c) {
		Assert.hasLength(c.getDescription());
		apply(new OrderCreatedEvent(c.getId(), c.getDescription()));
	}
	
	 @EventSourcingHandler
	 protected void on(OrderCreatedEvent cfe) {
	  this.orderId = cfe.getOrderId();
	 }
}
