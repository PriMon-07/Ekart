package com.infosys.ekart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import com.infosys.ekart.model.CartValue;
import com.infosys.ekart.model.OrderDetails;

@Service
public class FallbackMethods {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="getCartFallback")
	public CartValue getCart(String userId) {
		return restTemplate.getForObject("http://localhost:5000/"+userId+"/cart", CartValue.class);
	}
	
	public CartValue getCartFallback(String userId) {
		return new CartValue();
	}
	
	@HystrixCommand(fallbackMethod="getAddressIdfallback")
	public Integer getAddressId (OrderDetails orderDetails) {
		return restTemplate.postForObject("http://localhost:5000", orderDetails.getAddress(), Integer.class);
	}

	public Integer getAddressIdfallback (OrderDetails orderDetails) {
		return 0;
	}
	
	@HystrixCommand(fallbackMethod="getCardIdfallback")
	public Long getCardId (OrderDetails orderDetails) {
		return restTemplate.postForObject("http://localhost:5000", orderDetails.getCard(), Long.class);
	}

	public Long getCardIdfallback (OrderDetails orderDetails) {
		return 0l;
	}
	
	
}
