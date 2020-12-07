package com.infosys.ekart.sellerOne.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infosys.ekart.sellerOne.dto.BaseResponse;
import com.infosys.ekart.sellerOne.dto.NotificationModel;
import com.infosys.ekart.sellerOne.dto.ProductDTO;
import com.infosys.ekart.sellerOne.dto.ProductDetailsDTO;
import com.infosys.ekart.sellerOne.dto.SellerDTO;
import com.infosys.ekart.sellerOne.dto.StringResponse;
import com.infosys.ekart.sellerOne.exception.ProductNameAlreadyPresent;
import com.infosys.ekart.sellerOne.service.SellerService;

@RestController
public class SellerController {

	@Autowired
	Environment environment;

	@Autowired
	RestTemplate restTemplate;
	

	
	/*US36*/
	@RequestMapping(value = "/product/{productName}/modify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StringResponse> modifyProductBySeller(@RequestBody SellerDTO sellerDTO,
			@PathVariable String productName) {
		String productURI = "http://ProductMS";
		ResponseEntity<StringResponse> response;
		response = restTemplate.postForEntity(productURI+"/product/"+productName+"/modify/sellerOne", sellerDTO, StringResponse.class);
		return new ResponseEntity<StringResponse>(new StringResponse(response.getBody().getMessage()), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/product/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> addProducts(@PathVariable String sellerId, @Valid @RequestBody ProductDTO productDTO,
			BindingResult result) throws ProductNameAlreadyPresent {
		//logger.info("adding product to product table");
		String productURI = "http://ProductMS";
		// return restTemplate.exchange(OfferURI+"/offer/"+simId,HttpMethod.GET,entity,
		// SimOfferDTO.class).getBody();
		return restTemplate.postForEntity(productURI + "/product/add/sellerOne", productDTO,String.class);
	}

	// Ashish US-34,35
	@RequestMapping(value = "/products", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<ProductDetailsDTO> getProducts(@PathVariable String sellerId) {
		//logger.info("getting product from orderMS for : " + sellerId);
		String productURI = "http://ProductMS";
		// return restTemplate.exchange(OfferURI+"/offer/"+simId,HttpMethod.GET,entity,
		// SimOfferDTO.class).getBody();
		return restTemplate.exchange(productURI + "/products/sellerOne", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<ProductDetailsDTO>>() {
				}).getBody();
	}

    //Babita  US-37
		@RequestMapping(value = "/productsincart", method = RequestMethod.GET, headers = "Accept=application/json")
		public List<ProductDetailsDTO> getProductsInCart(@PathVariable String sellerId) {
			String productURI = "http://ProductMS";
			return restTemplate.exchange(productURI + "/productsincart/sellerOne", HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ProductDetailsDTO>>() {
					}).getBody();
		}
}
