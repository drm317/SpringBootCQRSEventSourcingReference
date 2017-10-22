package com.cloudnative.reference.handlers;

import com.cloudnative.reference.domain.AnOrder;
import com.cloudnative.reference.events.OrderCreatedEvent;
import com.cloudnative.reference.repository.OrderRepository;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.replay.ReplayAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderViewEventHandler implements ReplayAware {

  @Autowired
  private OrderRepository orderRepository;

  @EventHandler
  public void handle(OrderCreatedEvent event) {
    orderRepository.save(new AnOrder(event.getId(), event.getDescription()));
  }

  public void beforeReplay() {
  }

  public void afterReplay() {
  }

  public void onReplayFailed(Throwable cause) {
  }

}
