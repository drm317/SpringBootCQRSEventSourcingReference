package com.cloudnative.reference.controller;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.test.web.servlet.MvcResult;

import com.cloudnative.reference.OrderServiceApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = { OrderRestControllerTest.Config.class,
		OrderServiceApplication.class }, webEnvironment = MOCK)
public class OrderRestControllerTest {

	@Configuration
	public static class Config {

	}

	@Autowired
	private MockMvc mockMvc;
	
	private String orderJson, commentJson;

	private Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private ObjectMapper objectMapper;

	@Before
	 public void setUp() throws Throwable {

	  Map<String, Object> map;

	  map = new HashMap<>();
	  map.put("description", "Exercise Bicycle");
	  this.orderJson = this.objectMapper.writeValueAsString(map);

	  map = new HashMap<>();
	  map.put("comment", "Arrived quickly. Thanks.");
	  map.put("user", "le");
	  map.put("when", new Date());
	  this.commentJson = this.objectMapper.writeValueAsString(map);

	  this.log.debug("comment JSON: " + this.commentJson);
	  this.log.debug("order JSON: " + this.orderJson);
	 }
	
	@Test
	public void createOrder() throws Exception {
		createNewOrder();
	}

	private String createNewOrder() throws Exception {
		MvcResult result = this.mockMvc
				.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(this.orderJson))
				.andExpect(request().asyncStarted()).andReturn();

		AtomicReference<String> orderId = new AtomicReference<>();

		this.mockMvc.perform(asyncDispatch(result)).andExpect(mvcResult -> {
			String location = mvcResult.getResponse().getHeader("Location");
			String orderPath = "/order/";
			Assert.assertTrue(location.contains(orderPath));
			orderId.set(location.split(orderPath)[1]);
		}).andExpect(status().isCreated());

		return orderId.get();

	}
}
