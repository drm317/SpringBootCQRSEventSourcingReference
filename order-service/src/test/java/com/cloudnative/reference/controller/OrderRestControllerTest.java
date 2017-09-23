package com.cloudnative.reference.controller;

import static org.junit.Assert.fail;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cloudnative.reference.OrderServiceApplication;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = { OrderRestControllerTest.Config.class,
 OrderServiceApplication.class }, webEnvironment = MOCK)
public class OrderRestControllerTest {

	 

	public class Config {

	}

	private Log log = LogFactory.getLog(getClass());

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
