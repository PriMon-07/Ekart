package com.infosys.ekart.sellerOne;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SellerOneConfig {
	@Bean @LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
