package com.infosys.ekart.model;

public class PriceComparisonModel {
	private String displayName;
	private String sellerName;
	private float price;
	private float discount;
	private float deliveryCharge;
	private float offerPrice;
	private float rating;
	public PriceComparisonModel() {}
	public PriceComparisonModel(String displayName, String sellerName, float price, float discount,
			float deliveryCharge, float offerPrice, float rating) {
		super();
		this.displayName = displayName;
		this.sellerName = sellerName;
		this.price = price;
		this.discount = discount;
		this.deliveryCharge = deliveryCharge;
		this.offerPrice = offerPrice;
		this.rating = rating;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public float getDeliveryCharge() {
		return deliveryCharge;
	}
	public void setDeliveryCharge(float deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}
	public float getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(float offerPrice) {
		this.offerPrice = offerPrice;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "ProductComparisonModel [displayName=" + displayName + ", sellerName=" + sellerName + ", price=" + price
				+ ", discount=" + discount + ", deliveryCharge=" + deliveryCharge + ", offerPrice=" + offerPrice
				+ ", rating=" + rating + "]";
	}
}
