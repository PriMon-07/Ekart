package com.infosys.ekart.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infosys.ekart.exception.UserNotRegisteredException;
import com.infosys.ekart.service.CancelOrderService;
import com.infosys.ekart.utility.Response;

@RestController
@CrossOrigin
public class CancelOrderController {

	@Autowired
	private CancelOrderService cancelOrderService;

	Logger logger = LogManager.getLogger();

	@PostMapping(value = "/{userId}/orders/{orderId}/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> cancelOrder(@PathVariable String userId, @PathVariable long orderId) {
		logger.info(
				"Controller.WishList.getAllProductsFromWishList.Request accepted to get all products from wishlist");
		Boolean cancelStatus = false;
		try {
//			String checkLogInURL = WishListConstants.checkLogInURL;
//			Boolean loggedIn = new RestTemplate().getForObject(checkLogInURL, Boolean.class);
//			if (loggedIn) {
				cancelStatus = cancelOrderService.cancelOrder(userId, orderId);
				if (cancelStatus) {
					Response response = new Response("cancelled successfully");
					return new ResponseEntity<Response>(response, HttpStatus.OK);
				} else {
					Response response = new Response("Failed to cancel the order");
					return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
////				}
//			} else {
//				logger.error("The given user is not a registered buyer.");
//				throw new UserNotRegisteredException("CancelOrderController.The given user is not a registered buyer");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (e.getMessage().contains("OrderService")) {
				Response response = new Response("Failed to cancel the order as it is not in open state");
				return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
			} else if (e.getMessage().contains("CancelOrderController")) {
				Response response = new Response("Given user is not registered");
				return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
			} else {
				Response response = new Response(e.getMessage());
				return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

}
