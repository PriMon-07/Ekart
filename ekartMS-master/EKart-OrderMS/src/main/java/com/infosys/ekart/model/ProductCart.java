package com.infosys.ekart.model;

public class ProductCart {
	
	private String productName;
	private String category;
	private String sellerName;
	private Float price;
	private Float deliveryCharge;
	private Integer quantity;
	private Float total;

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}


}
