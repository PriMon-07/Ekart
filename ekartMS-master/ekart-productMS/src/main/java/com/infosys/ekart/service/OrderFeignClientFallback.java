package com.infosys.ekart.service;

import java.util.Set;
import java.util.TreeSet;

public class OrderFeignClientFallback implements OrderFeignClient{

	@Override
	public Set<String> getCategorySet(String userId) {
		return new TreeSet<>();
	}

}
