package com.cloudnative.reference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class HystrixDashboardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardServiceApplication.class, args);
	}
}
