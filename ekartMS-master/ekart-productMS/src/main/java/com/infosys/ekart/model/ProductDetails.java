package com.infosys.ekart.model;

import java.util.List;

public class ProductDetails {
	
	private String displayName;
	private String shortDesc;
	private String desc;
	private String category;
	private Float price;
	private Float discount;
	private Float deliveryCharge;
	private String seller;
	private Integer sellerCount;
	private Double avgRating;
	private List<Reviews> reviews;
	
	
	
	public Integer getSellerCount() {
		return sellerCount;
	}
	public void setSellerCount(Integer sellerCount) {
		this.sellerCount = sellerCount;
	}
	public Double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}
	public List<Reviews> getReviews() {
		return reviews;
	}
	public void setReviews(List<Reviews> reviews) {
		this.reviews = reviews;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}


}
