package com.infosys.ekart.wishlist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "wishListTable")
@GenericGenerator(name = "gen", strategy = "increment")
public class WishListEntity {

	@Id
	@GeneratedValue(generator = "gen")
	@Column(name = "wishlistid", nullable = false)
	Long wishListId;

	@Column(name = "userid", nullable = false)
	String userId;

	@Column(name = "productname", nullable = false)
	String productName;

	@Column(name = "displayname", nullable = false)
	String displayName;

	@Column(name = "category", nullable = false)
	String category;

	@Column(name = "shortdescription")
	String shortDesc;

	public WishListEntity() {

	}

	public WishListEntity(Long wishListId, String userId, String productName, String displayName, String category,
			String shortDesc) {
		this.wishListId = wishListId;
		this.userId = userId;
		this.productName = productName;
		this.displayName = displayName;
		this.category = category;
		this.shortDesc = shortDesc;
	}

	public Long getWishListId() {
		return wishListId;
	}

	public void setWishListId(Long wishListId) {
		this.wishListId = wishListId;
	}

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

}
