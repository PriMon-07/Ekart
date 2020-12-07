package com.infosys.ekart.sellerOne.dto;

public class ProductDetailsDTO {
	private String displayName;
	private String category;
	private Integer itemsSold;
	private Float totalAmount;
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
	public Integer getItemsSold() {
		return itemsSold;
	}
	public void setItemsSold(Integer itemsSold) {
		this.itemsSold = itemsSold;
	}
	public Float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}
	

}
