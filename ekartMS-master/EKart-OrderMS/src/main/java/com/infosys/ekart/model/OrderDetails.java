package com.infosys.ekart.model;

import java.util.List;

public class OrderDetails {
	
	private List<ProductCart> orderItems;
	private Address addressDetails;
	private Card paymentMethod;
	
	
	public List<ProductCart> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<ProductCart> orderItems) {
		this.orderItems = orderItems;
	}
	public Address getAddress() {
		return addressDetails;
	}
	public void setAddress(Address address) {
		this.addressDetails = address;
	}
	public Card getCard() {
		return paymentMethod;
	}
	public void setCard(Card card) {
		this.paymentMethod = card;
	}
	
	

}
