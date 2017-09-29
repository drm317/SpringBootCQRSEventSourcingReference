package com.cloudnative.reference;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletResponse;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class OrderRestController {

	private final CommandGateway commandGateway;

	@Autowired
	OrderRestController(CommandGateway cg) {
		this.commandGateway = cg;
	}

	@RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
	public void add(@PathVariable(value = "id") String id,
			@RequestParam(value = "description", required = true) String description, HttpServletResponse response) {
		CreateOrderCommand command = new CreateOrderCommand(id, description);
		commandGateway.sendAndWait(command);
		response.setStatus(HttpServletResponse.SC_CREATED);
		return;
	}

}
