package com.cloudnative.reference.handlers;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.replay.ReplayAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloudnative.reference.domain.Order;
import com.cloudnative.reference.events.OrderCreatedEvent;
import com.cloudnative.reference.repository.OrderRepository;

public class OrderViewEventHandler implements ReplayAware {

	private static final Logger LOG = LoggerFactory.getLogger(OrderViewEventHandler.class);

    @Autowired
    private OrderRepository orderRepository;

    @EventHandler
    public void handle(OrderCreatedEvent event) {
        orderRepository.save(new Order(event.getOrderId(), event.getDescription()));
    }
    
    @Override
    public void beforeReplay() {
        LOG.info("Event Replay is about to START. Clearing the View...");
    }

    @Override
    public void afterReplay() {
        LOG.info("Event Replay has FINISHED.");
    }

    @Override
    public void onReplayFailed(Throwable cause) {
        LOG.error("Event Replay has FAILED.");
    }

}
