package com.infosys.ekart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ekart.client.AuthenticationClient;
import com.infosys.ekart.entitiy.ProductEntity;
import com.infosys.ekart.model.CartDTO;
import com.infosys.ekart.model.CartValueModel;
import com.infosys.ekart.model.StringResponse;
import com.infosys.ekart.service.CartService;

@RestController
@CrossOrigin
public class CartController {
	@Autowired
	private CartService cartService;

	/* added by dhanapriya */
	@Autowired
	Environment environment;

	@Autowired
	private AuthenticationClient authenticationClient;
	/*---------------------*/

	@RequestMapping(value = "/{userId}/cart", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CartValueModel> viewCart(@PathVariable String userId) throws Exception {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		CartValueModel cartValueModel = new CartValueModel();
		try {
			cartValueModel = cartService.viewCart(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<CartValueModel>(cartValueModel, status);
	}

	/* added by dhanapriya*/
	/*US08*/
	@RequestMapping(value = "/{userId}/addtocart", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringResponse> addToCart(@RequestBody CartDTO cartDTO, @PathVariable String userId)
			throws Exception {

		String servResponse = "";
		StringResponse response = new StringResponse();
		try {
			// getproduct details
			List<ProductEntity> prodDetails = authenticationClient.getProductDetails(cartDTO.getDisplayName());
			if (prodDetails.isEmpty() || prodDetails == null) {
				throw new Exception("PRODUCT.NOT_FOUND");
			}
			for (ProductEntity p : prodDetails) {
				if (p.getSellerId().equalsIgnoreCase(cartDTO.getSellerName())) {
					cartDTO.setDeliveryCharge(p.getDeliveryCharge());
					cartDTO.setPrice(p.getPrice());
				}
			}
			servResponse = cartService.addToCart(cartDTO, userId);
			response.setMessage(servResponse);
			if (userId.equals("guest")) {
				// setting guest Id as response
				response.setMessage(servResponse);
			} else {
				// setting success msg for user
				response.setMessage(environment.getProperty("Login.user"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return new ResponseEntity<StringResponse>(response, HttpStatus.OK);
	}
	
	/*US09*/
	@RequestMapping(value = "/{userId}/modifycart", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringResponse> modifyCart(@RequestBody CartDTO cartDTO, @PathVariable String userId)
			throws Exception {
		String servResponse = "";
		StringResponse response = new StringResponse();
		try {
			servResponse = cartService.modifyCart(cartDTO, userId);
			response.setMessage(environment.getProperty(servResponse));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return new ResponseEntity<StringResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{guestId}/update/{newUserId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringResponse> updateGuestId(@PathVariable String guestId, @PathVariable String userId)
			throws Exception {
		String servResponse = "";
		StringResponse response = new StringResponse();
		try {
			servResponse = cartService.updateGuestId(guestId, userId);
			response.setMessage(environment.getProperty(servResponse));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return new ResponseEntity<StringResponse>(response, HttpStatus.OK);
	}
	/*------------------*/
	
	// Get count of items in the cart /{userId}/cartcount
	@GetMapping(value = ApplicationConstants.GET_CART_COUNT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> ViewCartCount(@PathVariable String userId) {

		logger.info("In ViewCartCount Controller");
		Integer count = cartService.viewCartCount(userId);
		Map<String, String> response = new HashMap<>();
		response.put("data", count.toString());

		return response;

	}
	
	
	//Get seller cart search
	@GetMapping(value = ApplicationConstants.GET_SELLER_CART, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CartDTO> searchCartSeller(@PathVariable String sellerName) {

		logger.info("In searchCartSeller controller");

		List<CartDTO> response = cartService.searchCartSeller(sellerName);
		if (response == null) {
			return null;
		}
		return response;
	}
	
	
	// cart/seller/updateprice
	@HystrixCommand(fallbackMethod = "updateOfferPriceFallback")
	@PostMapping(value = ApplicationConstants.POST_CART_UPDATE_PRICE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> updateOfferPrice(@RequestBody CartDTO cartDTO) throws Exception{
		logger.info("In updateOfferPrice controller");
		Map<String, String> response = new HashMap<>();
		try {
			if (cartService.updateOfferPrice(cartDTO) == true) {
				System.out.println("offer added success");
				response.put("data", "success");
			} else {
				response.put("data", "Failure");
			}
			return response;
		}
		catch(Exception e) {
			response.put("data", "Failure");
			return response;
		}
		
	}
	
	public Map<String, String> updateOfferPriceFallback(@RequestBody CartDTO cartDTO) {
		logger.info("In updateOfferPriceFallback controller");
		Map<String, String> response = new HashMap<>();
		response.put("data", "Update offer price failed....");
		return response;
	}

}