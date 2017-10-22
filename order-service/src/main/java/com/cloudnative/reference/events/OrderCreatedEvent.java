package com.cloudnative.reference.events;

import java.io.Serializable;

public class OrderCreatedEvent implements Serializable {

  private String id;

  private String description;

  public OrderCreatedEvent() {
  }

  public OrderCreatedEvent(String id, String description) {
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
