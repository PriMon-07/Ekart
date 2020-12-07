package com.infosys.ekart.controller;



import java.net.http.HttpResponse;
import java.util.List;
import java.util.Set;
import org.springframework.core.env.Environment;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ekart.model.CartValue;
import com.infosys.ekart.model.OrderDetails;
import com.infosys.ekart.model.ProductCartValue;
import com.infosys.ekart.model.ProductDetailsDTO;
import com.infosys.ekart.service.CheckOutService;
import com.infosys.ekart.service.FallbackMethods;

@Controller
@RestController
public class PlaceOrderController {
	
	
	@Autowired
	private FallbackMethods fallback;
	@Autowired
	private CheckOutService Service;
	@Autowired
	private Environment env; 
	
	Logger logger = LogManager.getLogger(this.getClass());
	
	@RequestMapping(value="/{userId}/checkout",method=RequestMethod.GET)
	public ResponseEntity<ProductCartValue> itemsInCart(@PathVariable String userId) {
		
		try {
		CartValue cartValue = fallback.getCart(userId);
		return new ResponseEntity<ProductCartValue>( Service.checkout(cartValue),HttpStatus.OK);
		}
		catch(Exception e) {
			//logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{userId}/orders/add",method=RequestMethod.POST)
	public ResponseEntity<String> placeOredr(@PathVariable String userId, @RequestBody OrderDetails orderDetails) {

		try {
			if (orderDetails.getAddress().getAddressId()==null) {
				Integer addressId = fallback.getAddressId(orderDetails);
				orderDetails.getAddress().setAddressId(addressId);}

			if (orderDetails.getCard().getCardId()==null) {
				Integer cardId = Integer.parseInt(fallback.getCardId(orderDetails).toString());
				orderDetails.getCard().setCardId(cardId);}

			System.out.println(orderDetails.getAddress().getCity());
			System.out.println(orderDetails.getCard().getCardNumber());
			String status = Service.placeOrder(orderDetails,userId);
			String response = new String(status);
			return new ResponseEntity<String>(response,HttpStatus.OK);
		}
		catch(Exception e) {
			//logger.error(e.getMessage());
			String response ;
			if (env.getProperty(e.getMessage())!=null) {
				 response = new String(env.getProperty(e.getMessage()).toString());}
			else {
				 response = new String(e.getMessage().toString());}
			return new ResponseEntity<String>(response,HttpStatus.BAD_REQUEST);

		}
	}
	
	
	@RequestMapping(value="/{userId}/getcategories",method=RequestMethod.GET)
	public Set<String> getCategory(@PathVariable String userId) {
		
		try {
		
		return Service.getCatagories(userId);
		}
		catch(Exception e) {
			//logger.error(e.getMessage());
			System.out.println(e);
			return null;
		}
		
	}
	 
		@RequestMapping(value="/getItemsSold/{sellerId}",method=RequestMethod.GET)
		public List<ProductDetailsDTO> getItemSold(@PathVariable String sellerId) {
			
			try {
			
			return Service.getItemsSold(sellerId);
			}
			catch(Exception e) {
				logger.error(e.getMessage());
				System.out.println(e);
				return null;
			}
			
		}

}
