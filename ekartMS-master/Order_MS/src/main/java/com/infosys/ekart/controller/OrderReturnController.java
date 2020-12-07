package com.infosys.ekart.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ekart.exception.InvalidOrderId;
import com.infosys.ekart.exception.OrderNotDelivered;
import com.infosys.ekart.service.OrderReturnService;

@RestController
@CrossOrigin
public class OrderReturnController {
	@Autowired
	private OrderReturnService orderReturnService;
	
	Logger logger = LogManager.getLogger(this.getClass());
	
	@RequestMapping(value = "/{userId}/orders/{orderId}/return", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> returnOrder(@PathVariable String userId, @PathVariable long orderId) throws Exception {
		logger.info("returnrning order for orderId and userId", userId, orderId);
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message;
		try {
			orderReturnService.startReturn(userId, orderId);
			status = HttpStatus.OK;
			message = "\"" + "Order set for Return"+ "\"";
		} catch (InvalidOrderId | OrderNotDelivered e) {
			status = HttpStatus.BAD_REQUEST;
			message = "\"" + e.getMessage() + "\""; 
			System.out.println(message);
			logger.warn(e.getMessage());
		} 
		return new ResponseEntity<String>(message,status);
	}
}