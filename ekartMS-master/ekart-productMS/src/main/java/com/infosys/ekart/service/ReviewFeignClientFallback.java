package com.infosys.ekart.service;

import java.util.ArrayList;
import java.util.List;

public class ReviewFeignClientFallback implements ReviewFeignClient{

	@Override
	public List<Integer> getRatings(String productName) {
		return new ArrayList<>();
	}

}
