package com.cloudnative.reference.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AnOrder {

  @Id
  private String id;

  private String description;

  public AnOrder(String id, String description) {
    this.id = id;
    this.description = description;
  }

  public AnOrder() {
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
