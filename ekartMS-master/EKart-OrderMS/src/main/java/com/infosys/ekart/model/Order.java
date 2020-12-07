package com.infosys.ekart.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.infosys.ekart.entity.OrderEntity;

public class Order {
	
	private Integer orderId;
	private String userId;
	private Address address;
	private Card card;
	private String productName;
	private String category;
	private String sellerName;
	private Float price;
	private Date orderedDate;
	private Date deliveryDate;
	private String orderStatus;
	private Integer quantity;
	
	
	
	
	public Integer getOrderId() {
		return orderId;
	}




	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}




	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}




	public Address getAddress() {
		return address;
	}




	public void setAddress(Address address) {
		this.address = address;
	}




	public Card getCard() {
		return card;
	}




	public void setCard(Card card) {
		this.card = card;
	}




	public String getProductName() {
		return productName;
	}




	public void setProductName(String productName) {
		this.productName = productName;
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




	public Float getPrice() {
		return price;
	}




	public void setPrice(Float price) {
		this.price = price;
	}




	public Date getOrderedDate() {
		return orderedDate;
	}




	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}




	public Date getDeliveryDate() {
		return deliveryDate;
	}




	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}




	public String getOrderStatus() {
		return orderStatus;
	}




	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}




	public Integer getQuantity() {
		return quantity;
	}




	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}




	public static OrderEntity toEntity(Order order) {
		OrderEntity entity = new OrderEntity();
		entity.setAddressId(order.getAddress().getAddressId());
		entity.setCardId(order.getCard().getCardId());
		entity.setCategory(order.getCategory());
		entity.setproductName(order.getProductName());
		entity.setPrice(order.getPrice());
		entity.setQuantity(order.getQuantity());
		entity.setSellerName(order.getSellerName());
		entity.setDeliveryDate(order.getDeliveryDate());
		entity.setOrderedDate(order.getOrderedDate());
		entity.setOrderStatus(order.getOrderStatus());
		entity.setUserId(order.getUserId());
		
		return entity;
	}
	

}
