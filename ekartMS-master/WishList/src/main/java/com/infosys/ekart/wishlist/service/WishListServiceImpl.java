package com.infosys.ekart.wishlist.service;

import static com.infosys.ekart.wishlist.utility.WishListConstants.ERROR_ON_SERVER;
import static com.infosys.ekart.wishlist.utility.WishListConstants.ITEM_ADDED;
import static com.infosys.ekart.wishlist.utility.WishListConstants.ITEM_ALREADY_EXIST;
import static com.infosys.ekart.wishlist.utility.WishListConstants.NO_ITEMS;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.infosys.ekart.wishlist.entity.WishListEntity;
import com.infosys.ekart.wishlist.exception.ErrorOnServerException;
import com.infosys.ekart.wishlist.exception.WishListEmptyException;
import com.infosys.ekart.wishlist.model.WishList;
import com.infosys.ekart.wishlist.repository.WishListRepository;

@Service
public class WishListServiceImpl implements WishListService {
	@Autowired
	WishListRepository wishListRepository;

	@Autowired
	private Environment environment;

	public String addProductToWishList(WishList wishList) throws ErrorOnServerException {
		String response = null;
		WishListEntity checkExisting = null;
		try {
			checkExisting = wishListRepository.findByUserIdAndProductName(wishList.getUserId(),
					wishList.getProductName());
			if (checkExisting != null) {
				if (wishList.getProductName().equalsIgnoreCase(checkExisting.getProductName())
						&& wishList.getUserId().equalsIgnoreCase(checkExisting.getUserId())) {
					response = environment.getProperty(ITEM_ALREADY_EXIST);
				}
			} else {
				WishListEntity wishlistEntity = WishList.createEntity(wishList);
				wishListRepository.saveAndFlush(wishlistEntity);
				response = environment.getProperty(ITEM_ADDED);
			}
		} catch (DataAccessException e) {
			throw new ErrorOnServerException(environment.getProperty(ERROR_ON_SERVER));
		}
		return response;
	}

	@Override
	public List<WishList> getAllProductsFromWishList(String userId) throws ErrorOnServerException,WishListEmptyException {
		List<WishList> wishlists = new ArrayList<WishList>();
		try {

			List<WishListEntity> wishlistEntities = wishListRepository.findAllByUserId(userId);
			if (wishlistEntities == null) {
				throw new WishListEmptyException(environment.getProperty(NO_ITEMS));
			} else {
				for (WishListEntity wishListEntity : wishlistEntities) {
					WishList wishList = WishList.valueOf(wishListEntity);
					wishlists.add(wishList);
				}
			}
		} catch (DataAccessException e) {
			throw new ErrorOnServerException(environment.getProperty(ERROR_ON_SERVER));
		}
		return wishlists;
	}

	@Override
	public Boolean removeItemFromWishList(String userId, String productName) throws ErrorOnServerException {
		WishListEntity entityToDelete = null;
		try {
			entityToDelete = wishListRepository.findByUserIdAndProductName(userId, productName);
			if (entityToDelete != null) {
				wishListRepository.deleteById(entityToDelete.getWishListId());
				return true;
			} else {
				return false;
			}
		} catch (DataAccessException exception) {
			throw new ErrorOnServerException(environment.getProperty(ERROR_ON_SERVER));
		}
	}
}
