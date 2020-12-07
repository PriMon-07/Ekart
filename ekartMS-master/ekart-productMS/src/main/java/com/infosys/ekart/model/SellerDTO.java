/*added by dhanapriya*/
package com.infosys.ekart.model;

public class SellerDTO {
	private Integer sellerId;
	private String sellerName;
	private String productName;
	private Float price;
	private Float discount;
	private Float deliveryCharge;
	private Float cartOfferPrice;
	

	public Float getCartOfferPrice() {
		return cartOfferPrice;
	}

	public void setCartOfferPrice(Float cartOfferPrice) {
		this.cartOfferPrice = cartOfferPrice;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Float getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(Float deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

}
