package com.infosys.ekart.model;

public class CartCountDTO {
	private Integer numberOfRecords;

	public CartCountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartCountDTO(Integer cartCount) {
		super();
		this.numberOfRecords = cartCount;
	}

	public Integer getCartCount() {
		return numberOfRecords;
	}

	public void setCartCount(Integer cartCount) {
		this.numberOfRecords = cartCount;
	}
}
