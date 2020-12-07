package com.infosys.ekart.model;

import java.sql.Date;


public class CartDTO {
	private Integer cartId;
	private String displayName;
	private String sellerName;
	private Integer quantity;
	private String category;
	private String userId;
	private Float deliveryCharge;
	private Float total;
	private Float cartOfferPrice;
	private Float price;
	private Date date1;

	public Date getDate1() {
		return date1;
	}

	public void setDate1(java.util.Date date) {
		this.date1 = (Date) date;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(Float deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Float getCartOfferPrice() {
		return cartOfferPrice;
	}

	public void setCartOfferPrice(Float cartOfferPrice) {
		this.cartOfferPrice = cartOfferPrice;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer productId) {
		this.cartId = productId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public CartDTO() {
		super();
	}

	

	@Override
	public String toString() {
		return "CartDTO [cartId=" + cartId + ", displayName=" + displayName + ", sellerName=" + sellerName
				+ ", quantity=" + quantity + ", category=" + category + ", userId=" + userId + ", deliveryCharge="
				+ deliveryCharge + ", total=" + total + ", cartOfferPrice=" + cartOfferPrice + ", price=" + price
				+ ", date=" + date1 + "]";
	}

}
