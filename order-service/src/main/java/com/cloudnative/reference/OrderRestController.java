package com.cloudnative.reference;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class OrderRestController {

	private final CommandGateway commandGateway;

	@Autowired
	OrderRestController(CommandGateway cg) {
		this.commandGateway = cg;
	}

	@PostMapping
	public void createOrder(@RequestBody Map<String, String> body, HttpServletResponse response) {
		String id = UUID.randomUUID().toString();
		CreateOrderCommand command = new CreateOrderCommand(id, body.get("description"));	
        commandGateway.sendAndWait(command);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return;
	}

}