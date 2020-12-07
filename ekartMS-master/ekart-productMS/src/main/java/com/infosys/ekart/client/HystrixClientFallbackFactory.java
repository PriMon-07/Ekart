/*added by dhanapriya*/
package com.infosys.ekart.client;

import org.springframework.stereotype.Component;

import com.infosys.ekart.model.SellerDTO;

import feign.hystrix.FallbackFactory;

@Component
public class HystrixClientFallbackFactory implements FallbackFactory<AuthenticationClient> {
	@Override
	public AuthenticationClient create(Throwable cause) {
		return new AuthenticationClient() {
			@Override
			public String updateCart(SellerDTO sellerDTO,String userId, String productName) {
				return null;
			}
		};
	};
}