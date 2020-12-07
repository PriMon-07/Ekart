package com.infosys.ekart.model;

import com.infosys.ekart.entity.ProductEntity;

public class Product {

	private Integer productId;
	private String displayName;
	private String shortDesc;
	private String description;
	private String category;
	private Float price;
	private Float discount;
	private Float deliveryCharge;
	private String sellerId;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public static Product toDTO(ProductEntity entity) {
		Product product = new Product();
		product.setProductId(entity.getProductId());
		product.setDisplayName(entity.getDisplayName());
		product.setShortDesc(entity.getShortDesc());
		product.setDescription(entity.getDescription());
		product.setCategory(entity.getCategory());
		product.setPrice(entity.getPrice());
		product.setDiscount(entity.getDiscount());
		product.setDeliveryCharge(entity.getDeliveryCharge());
		product.setSellerId(entity.getSellerId());
		return product;

	}

	public static ProductEntity toEntity(Product product) {
		ProductEntity entity = new ProductEntity();
		entity.setProductId(product.getProductId());
		entity.setDisplayName(product.getDisplayName());
		entity.setShortDesc(product.getShortDesc());
		entity.setDescription(product.getDescription());
		entity.setCategory(product.getCategory());
		entity.setPrice(product.getPrice());
		entity.setDiscount(product.getDiscount());
		entity.setDeliveryCharge(product.getDeliveryCharge());
		entity.setSellerId(product.getSellerId());
		return entity;

	}

}
