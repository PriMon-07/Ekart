package com.infosys.ekart.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infosys.ekart.constants.ApplicationConstants;
import com.infosys.ekart.model.BaseResponse;
import com.infosys.ekart.model.NotificationModel;
import com.infosys.ekart.model.OrderModel;
import com.infosys.ekart.model.SellerOrderDTO;
import com.infosys.ekart.service.OrderDeliveryService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin
public class DeliveryController {
	
	Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	OrderDeliveryService orderService;
	
	@GetMapping(value = ApplicationConstants.GET_SELLER_ORDERS, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SellerOrderDTO> getSellerOrders(@PathVariable String seller_name) {
		
		logger.info("getting all orders for seller with sellerId {}", seller_name);
		List<OrderModel> orderDtos = orderService.getSellerOrders(seller_name);
		List<SellerOrderDTO> sellerDto = new ArrayList<SellerOrderDTO>();
		try {
			for (OrderModel orederModel : orderDtos) {
				SellerOrderDTO sellerOrder = new SellerOrderDTO();
				sellerOrder.setOrderId(orederModel.getOrderId());
				sellerOrder.setOrderedDate(orederModel.getOrderedDate());
				// BUGGY Needs to be fixed
				//sellerOrder.setDeliverDate(orederModel.getDeliveredDate());
				sellerOrder.setQuantity(orederModel.getQuantity());
				sellerOrder.setOrderStatus(orederModel.getOrderStatus());
				sellerOrder.setProductName(orederModel.getProductName());
				sellerOrder.setUserId(orederModel.getSellerName());
				sellerOrder.setTotalPrice(orederModel.getTotalPrice());
				sellerDto.add(sellerOrder);
			}
			return sellerDto;
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Error in getting orders for sellerID");
			return null;
		}
	}
	@HystrixCommand(fallbackMethod="deliverOrderFallback")
	@PostMapping(value = ApplicationConstants.POST_SELLER__ORDER_DELIVER, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> deliverOrder(@PathVariable String user_id, @PathVariable long order_id) throws Exception{
		logger.info("delivering order for orderId and userId", user_id, order_id);
		BaseResponse response = new BaseResponse();
		try {
			boolean status = orderService.deliverOrder(user_id, order_id);
			System.out.println("status"+status);
			logger.info("Info level log message");
			if(status) {
				response = new BaseResponse("success",HttpStatus.OK.value());
				return new ResponseEntity<>(response,HttpStatus.OK);
			} else {
				response = new BaseResponse("success",HttpStatus.BAD_REQUEST.value());
				return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Error in deliver order");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}
	public ResponseEntity<String> deliverOrderFallback(@PathVariable String userId, @PathVariable long orderId) {
		return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
