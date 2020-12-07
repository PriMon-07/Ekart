package com.infosys.ekart.model;

import java.sql.Date;


import com.infosys.ekart.entity.CartEntity;

public class CartDTO {
	private Integer cartId;
	private String productName;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public static CartEntity toCartEntity(CartDTO cdto) {
		CartEntity centity = new CartEntity();
		centity.setCategory(cdto.getCategory());
		centity.setCartId(cdto.getCartId());
		centity.setDisplayName(cdto.getProductName());
		centity.setQuantity(cdto.getQuantity());
		centity.setUserId(cdto.getUserId());
		centity.setDeliveryCharge(cdto.getDeliveryCharge());
		centity.setTotal(cdto.getPrice() * cdto.getQuantity());
		centity.setCartOfferPrice(cdto.getCartOfferPrice());
		centity.setSellerName(cdto.getSellerName());
		centity.setPrice(cdto.getPrice());
		centity.setDate(cdto.getDate1());
		return centity;
	}

	public static CartDTO toCartDTO(CartEntity centity) {
		CartDTO cdto = new CartDTO();
		cdto.setPrice(centity.getPrice());
		cdto.setCategory(centity.getCategory());
		cdto.setCartId(centity.getCartId());
		cdto.setProductName(centity.getDisplayName());
		cdto.setQuantity(centity.getQuantity());
		cdto.setUserId(centity.getUserId());
		cdto.setDeliveryCharge(centity.getDeliveryCharge());
		cdto.setTotal(centity.getPrice() * centity.getQuantity());
		cdto.setCartOfferPrice(centity.getCartOfferPrice());
		cdto.setSellerName(centity.getSellerName());
		cdto.setDate1(centity.getDate());
		return cdto;
	}

	@Override
	public String toString() {
		return "CartDTO [cartId=" + cartId + ", displayName=" + productName + ", sellerName=" + sellerName
				+ ", quantity=" + quantity + ", category=" + category + ", userId=" + userId + ", deliveryCharge="
				+ deliveryCharge + ", total=" + total + ", cartOfferPrice=" + cartOfferPrice + ", price=" + price
				+ ", date=" + date1 + "]";
	}

}
