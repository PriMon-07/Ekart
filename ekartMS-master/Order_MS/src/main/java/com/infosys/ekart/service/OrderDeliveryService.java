package com.infosys.ekart.service;

import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.ekart.entitiy.OrderEntity;
import com.infosys.ekart.model.OrderModel;
import com.infosys.ekart.repository.OrderRepository;

@Service
public class OrderDeliveryService {
	@Autowired
	private OrderRepository orderRepository;

	
	public List<OrderModel> getSellerOrders(String seller_name) {
		List<OrderEntity> orderList = orderRepository.findBySellerName(seller_name);
		List<OrderModel> orderDtos = new ArrayList<OrderModel>();
		for (OrderEntity order : orderList) {
			OrderModel odt = new OrderModel();
			odt.setOrderId(order.getOrderId());
			odt.setProductName(order.getProductName());
			odt.setQuantity(order.getQuantity());
			odt.setCategory(order.getCategory());
			odt.setSellerName(order.getSellerName());
			odt.setPrice(order.getPrice());
			odt.setTotalPrice(order.getPrice() * order.getQuantity());
			odt.setOrderedDate(order.getOrderedDate());
			odt.setOrderStatus(order.getOrderStatus());
			if (odt.getOrderStatus().equals("open")) {
				odt.setBtnCancel(true);
			}
			if (odt.getOrderStatus().equals("delivered")) {
				odt.setBtnReturn(true);
				odt.setBtnReviewProduct(true);
				odt.setBtnReviewSeller(true);
			}
			if (odt.getOrderStatus().equals("returned")) {
				odt.setBtnCancel(true);
				odt.setBtnReviewProduct(true);
				odt.setBtnReviewSeller(true);
			}
			orderDtos.add(odt);
		}
		return orderDtos;
	}

	public boolean deliverOrder(String user_id, long order_id)throws Exception {
		
		OrderEntity orderEntity = orderRepository.findByUserIdAndOrderId(user_id, order_id);
		try {
		if (orderEntity == null) {
			return false;
		}
		Date deliveryDate = Date.valueOf(LocalDate.now());
		orderEntity.setOrderStatus("delivered");
		orderEntity.setDeliveryDate(deliveryDate);
		orderRepository.saveAndFlush(orderEntity);
		return true;
		}catch(Exception e) {
			throw new Exception(e);
		}
	}
}
