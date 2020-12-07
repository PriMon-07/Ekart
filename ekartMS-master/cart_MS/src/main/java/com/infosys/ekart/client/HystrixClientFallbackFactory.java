/*added by dhanapriya*/
package com.infosys.ekart.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.infosys.ekart.entitiy.ProductEntity;

import feign.hystrix.FallbackFactory;

@Component
public class HystrixClientFallbackFactory implements FallbackFactory<AuthenticationClient> {
	@Override
	public AuthenticationClient create(Throwable cause) {
		return new AuthenticationClient() {
			@Override
			public List<ProductEntity> getProductDetails(String productName) {
				return new ArrayList<ProductEntity>();
			}
		};
	};
}