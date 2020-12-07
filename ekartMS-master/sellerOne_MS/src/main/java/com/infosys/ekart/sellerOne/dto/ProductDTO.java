package com.infosys.ekart.sellerOne.dto;


import javax.validation.constraints.NotEmpty;


public class ProductDTO {
	@NotEmpty
	private String displayName;
	@NotEmpty
	private Float price;
	@NotEmpty
	private Float discount;
	private Float deliveryCharge;
	private String category;
	private String description;
	private String shortDesc;
	private String sellerId ;
	
	public String getDisplayName() {
		return displayName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
	
	
	
	/*public Product createSellerProductDTO() {
		Product sellerOneProductDTO = new Product();
		sellerOneProductDTO.setCategory(this.getCategory());
		sellerOneProductDTO.setDescription(this.getDescription());
		sellerOneProductDTO.setShortDesc(this.getShortDesc());
		sellerOneProductDTO.setDisplayName(this.getDisplayName());
		return sellerOneProductDTO;
	}*/

}
