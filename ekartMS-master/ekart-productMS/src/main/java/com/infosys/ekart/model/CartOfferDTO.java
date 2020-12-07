package com.infosys.ekart.model;
public class CartOfferDTO {
	Integer quantity;
	Float cartOfferPrice;
    String productName;
	String sellerName;
	
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

	public Float getCartOfferPrice() {
		return cartOfferPrice;
	}

	public void setCartOfferPrice(Float cartOfferPrice) {
		this.cartOfferPrice = cartOfferPrice;
	}

	public CartOfferDTO(Integer quantity, Float cartOfferPrice) {
		super();
		this.quantity = quantity;
		this.cartOfferPrice = cartOfferPrice;
	}

	public CartOfferDTO() {
		super();
	}
}