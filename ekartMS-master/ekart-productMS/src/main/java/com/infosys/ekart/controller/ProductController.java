package com.infosys.ekart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infosys.ekart.client.AuthenticationClient;
import com.infosys.ekart.constants.ApplicationConstants;
import com.infosys.ekart.exception.CanNotBeEmptyException;
import com.infosys.ekart.exception.ProductNameAlreadyPresent;
import com.infosys.ekart.model.BaseResponse;
import com.infosys.ekart.model.CartOfferDTO;
import com.infosys.ekart.model.DealsModel;
import com.infosys.ekart.model.NotificationModel;
import com.infosys.ekart.model.PriceComparisonModel;
import com.infosys.ekart.model.Product;
import com.infosys.ekart.model.ProductDTO;
import com.infosys.ekart.model.ProductDetails;
import com.infosys.ekart.model.ProductDetailsDTO;
import com.infosys.ekart.model.RecommendationModel;
import com.infosys.ekart.model.Reviews;
import com.infosys.ekart.model.SellerDTO;
import com.infosys.ekart.model.StringResponse;
import com.infosys.ekart.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private Environment env;
	
	//added by dhanapriya
	@Autowired
	private AuthenticationClient authenticationClient;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = ApplicationConstants.GET_DEALS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DealsModel>> getDeals() {
		List<DealsModel> dealsList = new ArrayList<>();
		dealsList = productService.getDeals();
		return ResponseEntity.ok(dealsList);
	}

	@GetMapping(value = ApplicationConstants.GET_PRICE_COMPARISON, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PriceComparisonModel>> priceComparison(@PathVariable String productName) {
		List<PriceComparisonModel> priceComparisonList = new ArrayList<>();
		priceComparisonList = productService.priceComparison(productName);
		return ResponseEntity.ok(priceComparisonList);
	}

	@GetMapping(value = ApplicationConstants.GET_RECOMMENDATIONS, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecommendationModel>> getRecommendations(@PathVariable String userId) {
		List<RecommendationModel> recommendationsList = new ArrayList<>();
		recommendationsList = productService.getRecommendations(userId);
		return ResponseEntity.ok(recommendationsList);
	}

	// Ashish US-34,35
	@RequestMapping(value = "/product/add/{sellerId}", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> addProducts(@PathVariable String sellerId, @Valid @RequestBody ProductDTO productDTO,
			BindingResult result) throws ProductNameAlreadyPresent {
		logger.info("adding product to product table");
		productDTO.setSellerId(sellerId);
		productService.addProduct(productDTO);
		logger.info("product successfully added");
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// Ashish US-34,35
	@RequestMapping(value = "/products/{sellerId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<ProductDetailsDTO>> getProducts(@PathVariable String sellerId) {
		logger.info("getting product from orderMS for : " + sellerId);
		List<ProductDetailsDTO> listOfProductCatalog = productService.getProductCatalog(sellerId);
		return new ResponseEntity<List<ProductDetailsDTO>>(listOfProductCatalog, HttpStatus.OK);
	}

	// Ashish US-34,35
	@RequestMapping(value = "/product/update/{sellerId}", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Boolean> modifyPrice(@PathVariable String sellerId, @Valid @RequestBody ProductDTO productDTO,
			BindingResult result) throws CanNotBeEmptyException {
		if (result.hasErrors()) {
			throw new CanNotBeEmptyException(
					env.getProperty("ProductController.DISPLAY_NAME_OR_DISCOUNT_OR_PRICE_CANNOT_BE_EMPTY"));
		}
		logger.info("updating product price and discount");
		productService.updatePriceAndDiscount(sellerId, productDTO.getDisplayName(), productDTO.getPrice(),
				productDTO.getDiscount());
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	/*added by dhanapriya*/
	/*US36*/
@RequestMapping(value = "/product/{productName}/modify/{sellerId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> modifyProductBySeller(@RequestBody SellerDTO sellerDTO,
			@PathVariable String productName, @PathVariable String sellerId) {

		BaseResponse response = new BaseResponse();
		sellerDTO.setSellerName(sellerId);
		sellerDTO.setProductName(productName);
		String message = productService.modifyProduct(sellerDTO);
		if(message.contains("Success")) {
			//System.out.println(message);
			//sellerDTO.setCartOfferPrice(sellerDTO.getPrice()-(sellerDTO.getPrice()*sellerDTO.getDiscount()/100));
			//String externalMessage = authenticationClient.updateCart(sellerDTO,sellerDTO.getSellerName(),productName);
			//response.setMessage(env.getProperty(externalMessage));
			response.setMessage(env.getProperty(message));
			
		
		}
		else {
			response.setMessage(env.getProperty(message));
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<BaseResponse>(response, HttpStatus.BAD_REQUEST);
		}
		
		/*NotificationModel notification = productService.modifyProduct(sellerDTO);
		if (notification != null) {
			ResponseEntity<BaseResponse> response1 = restTemplate
					.postForEntity("http://NotificationMS/notifications/add", notification, BaseResponse.class);
			response.setMessage(response1.getBody().getMessage());
			return new ResponseEntity<StringResponse>(response, HttpStatus.OK);
		} else {
			response.setMessage(env.getProperty("product.notFound"));
			return new ResponseEntity<StringResponse>(response, HttpStatus.OK);
		}*/
		response.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}
	
	/*---------------*/
	
	
	@RequestMapping(value = "searchproduct/{productName}", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProduct(@PathVariable String productName) {
		try {
			List<Product> listedProd = productService.getProducts(productName);
			return new ResponseEntity<List<Product>>(listedProd, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "{productName}/details", method = RequestMethod.GET)
	public ResponseEntity<ProductDetails> getProductByname(@PathVariable String productName) {
		try {
			@SuppressWarnings("unchecked")
			List<Reviews> reviews = restTemplate.getForObject("http://localhost:5000" + productName + "reviewproduct",
					List.class);
			return new ResponseEntity<ProductDetails>(productService.getProductsByName(productName, reviews),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = ApplicationConstants.GET_USER_CART,  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CartDTO> displayUserCart(@PathVariable String sellerId) {
		logger.info("Displaying total product in cart");
		@SuppressWarnings("unchecked")
		UserCartDTO[] userCartDTOs;
		List<UserCartDTO> ucDTOs = new ArrayList<>();
		userCartDTOs = new RestTemplate().getForObject("http://localhost:5000/searchcartseller/" + sellerId,UserCartDTO[].class); 
		System.out.println("UserCartDTOs -> "+userCartDTOs);
		for (UserCartDTO user : userCartDTOs) {
			System.out.println("inside list->"+user);
			ucDTOs.add(user);
		}
		return productServiceImpl.displayUserCart(ucDTOs, sellerId);
	}
	// update cart price /cart/{seller}/updateprice
	@PostMapping(value = ApplicationConstants.POST_UPDATE_CART, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String modifyOfferCartPrice(@RequestBody CartOfferDTO cartOfferDTO,
			@PathVariable String userId, @PathVariable String productName) {
		logger.info("Updating cart of user  : {}  ", userId);
		cartOfferDTO.setProductName(productName);
		cartOfferDTO.setSellerName(userId);
		String status = new RestTemplate().postForObject("http://localhost:5000/cart/seller/updateprice", cartOfferDTO, String.class);
		logger.info("Offer price added");
		return status;
	}

	// /{sellerName}/sellerorders
	@GetMapping(value = ApplicationConstants.GET_SELLER_ORDER, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UiOrderDTO> getOrders(@PathVariable String sellerName) {
		logger.info("Orders of seller ", sellerName);
		@SuppressWarnings("unchecked")
		List<UiOrderDTO> orderDTOList = new RestTemplate().getForObject("http://localhost:8100/" + sellerName + "/getorders", List.class);
		return orderDTOList;

	}

	//change order status to deliver
	@GetMapping(value = ApplicationConstants.GET_DELIVER_ORDER, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deliverOrder(@PathVariable String userId,
			@PathVariable String orderId) {
		logger.info("Changing delivery status of user ", userId);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		//System.out.println(formatter.format(date));
		String status = new RestTemplate().postForObject(
				"http://localhost:5002/" + userId + "/orders/" + orderId + "/delivered", date, String.class);
		// ribbon
		NotificationDTO notificationDTO = new NotificationDTO();
			notificationDTO.setUserId(userId);
			notificationDTO.setMessage("your order with order id" + orderId + "is delivered");
			notificationDTO.setMessageType("Delivered");
			String status1 = new RestTemplate().postForObject("http://localhost:5002/notifications/add", notificationDTO, String.class);																				
		return "success";
	}
}
