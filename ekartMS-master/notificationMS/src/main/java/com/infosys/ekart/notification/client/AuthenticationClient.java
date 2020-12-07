package com.infosys.ekart.notification.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "AccountMS", fallbackFactory = HystrixClientFallbackFactory.class, primary = false)
public interface AuthenticationClient {
	@GetMapping("{userId}/checkuser")
	public Boolean authenticateUser(@PathVariable("userId") String userId);

}
