package com.infosys.ekart.wishlist.controller;

import static com.infosys.ekart.wishlist.utility.WishListConstants.ADD_ITEM;
import static com.infosys.ekart.wishlist.utility.WishListConstants.FETCH_ALL_ITEMS;
import static com.infosys.ekart.wishlist.utility.WishListConstants.REMOVE_ITEM;
import static com.infosys.ekart.wishlist.utility.WishListConstants.USER_NOT_LOGGED_IN;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ekart.wishlist.client.AuthenticationClient;
import com.infosys.ekart.wishlist.exception.AccountServiceException;
import com.infosys.ekart.wishlist.exception.ErrorOnServerException;
import com.infosys.ekart.wishlist.exception.UserNotLoggedInException;
import com.infosys.ekart.wishlist.model.WishList;
import com.infosys.ekart.wishlist.service.WishListService;
import com.infosys.ekart.wishlist.utility.Response;

@RestController
@CrossOrigin
public class WishListController {

	@Autowired
	WishListService wishListService;

	@Autowired
	private Environment env;

	@Autowired
	private AuthenticationClient authenticationClient;

	@PostMapping(ADD_ITEM)
	public ResponseEntity<Response> addProductToWishList(@RequestBody WishList wishList,
			@PathVariable("userid") String userId, @PathVariable("productname") String productName)
			throws ErrorOnServerException, AccountServiceException {

		String addStatus = null;

		wishList.setProductName(productName);
		wishList.setUserId(userId);

//		Boolean isAuthenticated = authenticationClient.authenticateUser(userId);
//		System.out.println(isAuthenticated);
//		if (!isAuthenticated) {
//			throw new UserNotLoggedInException(env.getProperty(USER_NOT_LOGGED_IN));
//		} else {
			addStatus = wishListService.addProductToWishList(wishList);
			if ("Added".equals(addStatus)) {
				Response response = new Response("Item added to wishlist successfully.");
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			} else if ("Exists already".equals(addStatus)) {
				Response response = new Response("Item is already in the wishlist");
				return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
			}
//		}
		return null;
	}

	@GetMapping(FETCH_ALL_ITEMS)
	public ResponseEntity<List<WishList>> fetchAllProductsFromWishList(@PathVariable("userid") String userId)
			throws ErrorOnServerException, AccountServiceException {
		List<WishList> products = new ArrayList<WishList>();

//		Boolean isAuthenticated = authenticationClient.authenticateUser(userId);
//		if (!isAuthenticated) {
//			throw new UserNotLoggedInException(env.getProperty(USER_NOT_LOGGED_IN));
//		} else {
			products = wishListService.getAllProductsFromWishList(userId);
			return new ResponseEntity<List<WishList>>(products, HttpStatus.OK);
//		}
	}

	@PostMapping(REMOVE_ITEM)
	public ResponseEntity<Response> removeItemFromWishList(@PathVariable("userid") String userId,
			@PathVariable("productname") String productName) throws ErrorOnServerException, AccountServiceException {

		Boolean deleteStatus = false;
		Response response = null;

//		Boolean isAuthenticated = authenticationClient.authenticateUser(userId);
//		System.out.println(isAuthenticated);
//		if (!isAuthenticated) {
//			throw new UserNotLoggedInException(env.getProperty(USER_NOT_LOGGED_IN));
//		}
		deleteStatus = wishListService.removeItemFromWishList(userId, productName);
		if (deleteStatus) {
			response = new Response("Item removed successfully");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response = new Response("No such item found.");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}

	}

}
