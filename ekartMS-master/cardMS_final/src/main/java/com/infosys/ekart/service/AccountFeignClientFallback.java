package com.infosys.ekart.service;

public class AccountFeignClientFallback implements AccountFeignClient{

	@Override
	public Boolean checkUser(String userId) {
		return false;
	}

}
