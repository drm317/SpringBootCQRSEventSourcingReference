package com.cloudnative.reference;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/order", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class OrderRestController {

	private final CommandGateway commandGateway;

	@Autowired
	OrderRestController(CommandGateway cg) {
		this.commandGateway = cg;
	}

	@PostMapping
	CompletableFuture<ResponseEntity<?>> createOrder(@RequestBody Map<String, String> body) {

		String id = UUID.randomUUID().toString();
		CreateOrderCommand orderCommand = new CreateOrderCommand(id, body.get("description"));
		return null;
	}

	private static URI uri(String uri, Map<String, String> template) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().path(uri).build().expand(template);
		return uriComponents.toUri();
	}

}
