package com.infosys.ekart.wishlist.model;

import com.infosys.ekart.wishlist.entity.WishListEntity;

public class WishList {

	String userId;
	String productName;
	String displayName;
	String shortDesc;
	String category;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public static WishListEntity createEntity(WishList wishList) {

		WishListEntity WishListEntity = new WishListEntity();
		WishListEntity.setUserId(wishList.userId);
		WishListEntity.setProductName(wishList.productName);
		WishListEntity.setDisplayName(wishList.displayName);
		WishListEntity.setCategory(wishList.category);
		WishListEntity.setShortDesc(wishList.shortDesc);

		return WishListEntity;
	}

	public static WishList valueOf(WishListEntity WishListEntity) {

		WishList WishList = new WishList();
		WishList.setUserId(WishListEntity.getUserId());
		WishList.setProductName(WishListEntity.getProductName());
		WishList.setDisplayName(WishListEntity.getDisplayName());
		WishList.setCategory(WishListEntity.getCategory());
		WishList.setShortDesc(WishListEntity.getShortDesc());

		return WishList;
	}

}
