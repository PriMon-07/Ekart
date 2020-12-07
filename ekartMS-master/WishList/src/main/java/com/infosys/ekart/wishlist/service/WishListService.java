package com.infosys.ekart.wishlist.service;

import java.util.List;

import com.infosys.ekart.wishlist.exception.ErrorOnServerException;
import com.infosys.ekart.wishlist.exception.WishListEmptyException;
import com.infosys.ekart.wishlist.model.WishList;

public interface WishListService {
	public String addProductToWishList(WishList wishlistDTO) throws ErrorOnServerException;

	List<WishList> getAllProductsFromWishList(String userId) throws ErrorOnServerException, WishListEmptyException;

	Boolean removeItemFromWishList(String userId, String productName) throws ErrorOnServerException;
}
