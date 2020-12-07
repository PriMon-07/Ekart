/*added by dhanapriya*/
package com.infosys.ekart.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.infosys.ekart.model.SellerDTO;


@FeignClient(name = "ProductMS", fallbackFactory = HystrixClientFallbackFactory.class, primary = false)
public interface AuthenticationClient {
	@PostMapping("/updatecart/{userId}/{productName}")
	public String updateCart(@RequestBody SellerDTO sellerDTO, @PathVariable("userId") String userId, @PathVariable("productName") String productName);
}