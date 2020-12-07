package com.infosys.ekart.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.ekart.entitiy.OrderEntity;
import com.infosys.ekart.repository.OrderRepository;
import com.infosys.ekart.exception.InvalidOrderCancellationException;
import com.infosys.ekart.exception.OrderNotExistException;

@Service
public class CancelOrderService {

	@Autowired
	private OrderRepository cancelOrderRepository;

	private static Logger logger = LogManager.getLogger();

	public Boolean cancelOrder(String userId, long orderId) throws Exception {
		OrderEntity orderEntity = null;
		try {
			logger.info("Request to cancel odrer received, with detials as orderId{" + orderId + "}");
			orderEntity = cancelOrderRepository.findByOrderId(orderId);
			if (orderEntity != null) {
				if (orderEntity.getOrderStatus().equalsIgnoreCase("open")) {
					orderEntity.setOrderStatus("cancelled");
					cancelOrderRepository.saveAndFlush(orderEntity);
					return true;
				} else {
					throw new InvalidOrderCancellationException("OrderService.ORDER_PRODUCTS_NOT_IN_OPEN_STATUS");
				}
			} else {
				logger.error("Request to cancel odrer failed beacuse no order exist with deitals as OrderId{" + orderId
						+ "}");
				throw new OrderNotExistException("OrderService.ORDER_DOES_NOT_EXIST");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

}
