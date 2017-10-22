package com.cloudnative.reference;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.jayway.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SystemHealthTest {

  public static final int PORT_FOR_GATEWAY = 8080;
  public static final int PORT_FOR_SERVICE_REGISTRY = 8761;
  public static final int PORT_FOR_CONFIG_SERVICE = 8888;
  public static final int PORT_FOR_ORDER_SERVICE = 8900;
  public static final int PORT_FOR_ORDER_QUERY_SERVICE = 8901;
  public static final String ORDER_SERVICE_ID = "ORDER-SERVICE";
  public static final String ORDER_QUERY_SERVICE_ID = "ORDER-QUERY-SERVICE";

  @Test
  public void testA_GatewayServiceShouldBeAvailable() {
    given().port(PORT_FOR_GATEWAY).when().get("/health/").then()
        .statusCode(HttpStatus.SC_OK).body("status", Matchers.is("UP"))
        .body("hystrix.status", Matchers.is("UP"));

    given().port(PORT_FOR_GATEWAY).when().get("/routes/").then()
        .statusCode(HttpStatus.SC_OK);
  }

  @Test
  public void testB_ServiceRegistryShouldBeAvailable() {
    given().port(PORT_FOR_SERVICE_REGISTRY).when().get("/health/").then()
        .statusCode(HttpStatus.SC_OK).body("status", Matchers.is("UP"));
  }

  @Test
  public void testC_ConfigurationServiceShouldBeAvailable() {
    given().port(PORT_FOR_CONFIG_SERVICE).when().get("/health/").then()
        .statusCode(HttpStatus.SC_OK).body("status", Matchers.is("UP"));
  }

  @Test
  public void testD_OrderServiceShouldBeAvailable() {
    given().port(PORT_FOR_ORDER_SERVICE).when().get("/health/").then()
        .statusCode(HttpStatus.SC_OK).body("status", Matchers.is("UP"))
        .body("rabbit.status", Matchers.is("UP"))
        .body("hystrix.status", Matchers.is("UP"));

    given().port(PORT_FOR_ORDER_SERVICE).when().get("/instances").then()
        .statusCode(HttpStatus.SC_OK)
        .body("serviceId", Matchers.hasItems(ORDER_SERVICE_ID))
        .body("instanceInfo.actionType", Matchers.hasItems("ADDED"));
  }

  @Test
  public void testE_OrderQueryServiceShouldBeAvailable() {
    given().port(PORT_FOR_ORDER_QUERY_SERVICE).when().get("/health/").then()
        .statusCode(HttpStatus.SC_OK).body("status", Matchers.is("UP"))
        .body("rabbit.status", Matchers.is("UP"))
        .body("hystrix.status", Matchers.is("UP"));

    given().port(PORT_FOR_ORDER_QUERY_SERVICE).when().get("/instances").then()
        .statusCode(HttpStatus.SC_OK)
        .body("serviceId", Matchers.hasItems(ORDER_QUERY_SERVICE_ID))
        .body("instanceInfo.actionType", Matchers.hasItems("ADDED"));
  }

}
