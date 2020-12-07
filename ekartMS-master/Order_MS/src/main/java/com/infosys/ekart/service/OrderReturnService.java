package com.infosys.ekart.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.infosys.ekart.entitiy.OrderEntity;
import com.infosys.ekart.exception.InvalidOrderId;
import com.infosys.ekart.exception.OrderNotDelivered;
import com.infosys.ekart.repository.OrderRepository;

@Service
public class OrderReturnService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private Environment environment;

	@SuppressWarnings("unused")
	public void startReturn(String userId, long orderId) throws InvalidOrderId, OrderNotDelivered {
		
		OrderEntity order = orderRepository.findByUserIdAndOrderId(userId, orderId);
		
//		Date cal = order.getOrderedDate();
//		Date now=new DateAndTime().getCurrentDateTime(); 
//		
//		int diffInDays = (int) ((cal.getTime() - now) / (1000 * 60 * 60 * 24)); 
//		
//		System.out.println("==================?>>>"+cal);
////		cal.add(Calendar.DAY_OF_MONTH, 5);
//		Calendar c = Calendar.getInstance();
//		System.out.println("=============>>" + c.toString());
		
		if (order == null) {
			throw new InvalidOrderId(environment.getProperty("WRONG_ORDER_ID"));
		} else if (!order.getOrderStatus().equalsIgnoreCase("delivered")) {
			throw new OrderNotDelivered(environment.getProperty("NOT_DELIVERED"));
		}
		order.setOrderStatus("returned");
		orderRepository.saveAndFlush(order);
		
	}
}
