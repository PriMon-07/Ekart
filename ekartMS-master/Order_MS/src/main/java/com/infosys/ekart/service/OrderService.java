package com.infosys.ekart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.ekart.entitiy.OrderEntity;
import com.infosys.ekart.model.OrderModel;
import com.infosys.ekart.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	public List<OrderModel> viewOrders(String userId) {

		List<OrderEntity> orderEntityList = orderRepository.findAllByUserId(userId);
		List<OrderModel> orderModelList = new ArrayList<>();

		for (OrderEntity order : orderEntityList) {
			OrderModel orderM = new OrderModel();

			orderM.setOrderId(order.getOrderId());
			orderM.setQuantity(order.getQuantity());
			orderM.setProductName(order.getProductName());
			orderM.setCategory(order.getCategory());
			orderM.setSellerName(order.getSellerName());
			orderM.setPrice(order.getPrice());
			orderM.setTotalPrice(order.getPrice() * order.getQuantity());
			orderM.setOrderedDate(order.getOrderedDate());
			orderM.setOrderStatus(order.getOrderStatus());
			if (orderM.getOrderStatus().equals("open")) {
				orderM.setBtnCancel(true);
			}
			else if (orderM.getOrderStatus().equals("delivered")) {
				orderM.setBtnReturn(true);
				orderM.setBtnReviewProduct(true);
				orderM.setBtnReviewSeller(true);
			}
			else if (orderM.getOrderStatus().equals("returned")) {
				orderM.setBtnCancel(true);
				orderM.setBtnReviewProduct(true);
				orderM.setBtnReviewSeller(true);
			}
			orderModelList.add(orderM);
		}

		return orderModelList;
	}

}
