package com.infosys.ekart.model;

public class CartDetails {
	private String displayName;
	private String category;
	private String sellerName;
	private Double price;
	private Double deliveryCharge;
	private Integer quantity;
	private Double total;
	private Double cartOfferPrice;

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

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(Double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getCartOfferPrice() {
		return cartOfferPrice;
	}

	public void setCartOfferPrice(Double cartOfferPrice) {
		this.cartOfferPrice = cartOfferPrice;
	}

	public CartDetails(String displayName, String category, String sellerName, Double price, Double deliveryCharge,
			Integer quantity, Double total, Double cartOfferPrice) {
		super();
		this.displayName = displayName;
		this.category = category;
		this.sellerName = sellerName;
		this.price = price;
		this.deliveryCharge = deliveryCharge;
		this.quantity = quantity;
		this.total = total;
		this.cartOfferPrice = cartOfferPrice;
	}

	public CartDetails() {
		super();
	}

	@Override
	public String toString() {
		return "CartDetails [displayName=" + displayName + ", category=" + category + ", sellerName=" + sellerName
				+ ", price=" + price + ", deliveryCharge=" + deliveryCharge + ", quantity=" + quantity + ", total="
				+ total + ", cartOfferPrice=" + cartOfferPrice + "]";
	}
}