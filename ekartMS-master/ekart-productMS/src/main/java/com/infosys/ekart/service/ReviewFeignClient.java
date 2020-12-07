package com.infosys.ekart.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infosys.ekart.constants.ApplicationConstants;

@FeignClient(name = ApplicationConstants.REVIEW_MS, fallback = ReviewFeignClientFallback.class)
public interface ReviewFeignClient {
	@RequestMapping(value = ApplicationConstants.GET_RATINGS)
	List<Integer> getRatings(@PathVariable(ApplicationConstants.PRODUCT_NAME) String productName);
}
