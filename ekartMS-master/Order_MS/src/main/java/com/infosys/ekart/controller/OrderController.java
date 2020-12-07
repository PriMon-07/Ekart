package com.infosys.ekart.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ekart.exception.UserNotLoggedInException;
import com.infosys.ekart.model.OrderModel;
import com.infosys.ekart.service.OrderService;
import com.infosys.ekart.client.AuthenticationClient;

@RestController
@CrossOrigin
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private Environment environment;

	@Autowired
	private AuthenticationClient authenticationClient;

	private static Logger logger = LogManager.getLogger();

	@RequestMapping(value = "/{userId}/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderModel>> viewOrder(@PathVariable String userId) throws Exception {
		logger.info("Viewing Orders for userId", userId);
		List<OrderModel> orderList = new ArrayList<>();
		HttpStatus status = HttpStatus.BAD_REQUEST;
		try {
			Boolean isAuthenticated = authenticationClient.authenticateUser(userId);
			if (!isAuthenticated) {
				throw new UserNotLoggedInException(environment.getProperty("USER_NOT_LOGGED_IN"));
			}
			orderList = orderService.viewOrders(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.warn(e.getMessage());
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<List<OrderModel>>(orderList, status);
	}
}