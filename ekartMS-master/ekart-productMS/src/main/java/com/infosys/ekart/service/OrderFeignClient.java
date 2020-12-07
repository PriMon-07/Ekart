package com.infosys.ekart.service;

import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infosys.ekart.constants.ApplicationConstants;

@FeignClient(name = ApplicationConstants.ORDER_MS, fallback = OrderFeignClientFallback.class)
public interface OrderFeignClient {
	@RequestMapping(value = ApplicationConstants.GET_CATEGORIES)
	Set<String> getCategorySet(@PathVariable(ApplicationConstants.USER_ID) String userId);
}
