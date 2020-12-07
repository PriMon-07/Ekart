/*added by dhanapriya*/
package com.infosys.ekart.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.infosys.ekart.entitiy.ProductEntity;

@FeignClient(name = "ProductMS", fallbackFactory = HystrixClientFallbackFactory.class, primary = false)
public interface AuthenticationClient {
	@GetMapping("searchproduct/{productName}")
	public List<ProductEntity> getProductDetails(@PathVariable("productName") String productName);
}