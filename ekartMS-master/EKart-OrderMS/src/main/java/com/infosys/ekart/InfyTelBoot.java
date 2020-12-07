package com.infosys.ekart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;

//@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@PropertySource(value = {"classpath:configuration.properties"})
@EnableEurekaClient
@EnableCircuitBreaker
public class InfyTelBoot {

	public static void main(String[] args) {
		SpringApplication.run(InfyTelBoot.class, args);
	}
	
}
