package com.infosys.ekart.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestBean {
	
	@Bean @LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
