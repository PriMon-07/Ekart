package com.infosys.ekart.notification.client;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class HystrixClientFallbackFactory implements FallbackFactory<AuthenticationClient> {
	@Override
	public AuthenticationClient create(Throwable cause) {
		return new AuthenticationClient() {
			@Override
			public Boolean authenticateUser(String userId) {
				return false;
			}
		};
	};
}
