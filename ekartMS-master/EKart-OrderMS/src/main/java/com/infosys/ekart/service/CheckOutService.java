package com.infosys.ekart.service;

import java.util.List;
import java.util.Set;

import com.infosys.ekart.model.CartValue;
import com.infosys.ekart.model.OrderDetails;
import com.infosys.ekart.model.ProductCartValue;
import com.infosys.ekart.model.ProductDetailsDTO;

public interface CheckOutService {

	ProductCartValue checkout(CartValue cartValue);

	String placeOrder(OrderDetails orederdetails, String userId) throws Exception;

	Set<String> getCatagories(String userId);
	
	List<ProductDetailsDTO> getItemsSold(String sellerId);

}
