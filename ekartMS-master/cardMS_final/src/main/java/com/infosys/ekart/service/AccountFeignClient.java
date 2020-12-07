package com.infosys.ekart.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "AccountMS", fallback = AccountFeignClientFallback.class)
public interface AccountFeignClient {
	@RequestMapping(value = "/{userId}/checkuser")
	Boolean checkUser(@PathVariable("userId") String userId);
}
