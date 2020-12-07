package com.infosys.ekart.model;

public class ProductDetailsDTO {
	private String displayName;
	private String category;
	private Integer itemsSold;
	private Float totalAmount;
	public ProductDetailsDTO() {
		
	}
	public ProductDetailsDTO(long itemsSold, double totalAmount,String name,String category) {
		this.displayName=name;
		this.category=category;
		this.itemsSold=(int)itemsSold;
		this.totalAmount=(float)totalAmount;
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
