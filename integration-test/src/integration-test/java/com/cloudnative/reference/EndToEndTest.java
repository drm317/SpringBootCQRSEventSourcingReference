package com.cloudnative.reference;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;

import static com.jayway.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EndToEndTest {

  private static String id;
  private static String description;

  public static final String CMD_ROUTE = "/os";
  public static final String QUERY_ROUTE = "/oqs";
  public static final String CMD_ORDER_CREATE = "/add";
  public static final String ORDERS_CMD_BASE_PATH = CMD_ROUTE + "/order";
  public static final String ORDERS_QRY_BASE_PATH = QUERY_ROUTE + "/orders";

  public static final int PORT_FOR_GATEWAY = 8080;

  @BeforeClass
  public static void setupClass() {
    id = UUID.randomUUID().toString();
    description = "End to End Test Description [" + id + "]";
  }

  @After
  public void afterEach() throws InterruptedException {
    TimeUnit.SECONDS.sleep(10l);
  }

  // Create an order with command service
  @Test
  public void testA_CreateAnOrder() {

    given().port(PORT_FOR_GATEWAY).header("Content-Type", "application/json")
        .when()
        .post(ORDERS_CMD_BASE_PATH + CMD_ORDER_CREATE
            + "/{id}?description={description}", id, description)
        .then().statusCode(HttpStatus.SC_CREATED);

  }

  // Ensure the order created event has been made available to the query service
  // for clients to view
  @Test
  public void testB_GetTheOrder() {
    given().port(PORT_FOR_GATEWAY).when()
        .get(ORDERS_QRY_BASE_PATH + "/{id}", id).then()
        .statusCode(HttpStatus.SC_OK)
        .body("description", Matchers.is(description));
  }
}
