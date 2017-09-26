package com.cloudnative.reference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.cloudnative.reference.prefilter.ThrottlingPreFilter;

@EnableZuulProxy
@SpringBootApplication
public class GatewayServiceApplication {

	@Bean
	public ThrottlingPreFilter throttlingPreFilter() {
		return new ThrottlingPreFilter();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}
}
