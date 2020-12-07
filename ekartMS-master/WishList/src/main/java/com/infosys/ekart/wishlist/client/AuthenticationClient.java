package com.infosys.ekart.wishlist.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//import static com.infosys.SimService.constant.Constants.SIM_VALIDATION_URI;

@FeignClient(name = "AccountMS", fallbackFactory = HystrixClientFallbackFactory.class, primary = false)
public interface AuthenticationClient {
	@GetMapping("{userId}/checkuser")
	public Boolean authenticateUser(@PathVariable("userId") String userId);

} 
