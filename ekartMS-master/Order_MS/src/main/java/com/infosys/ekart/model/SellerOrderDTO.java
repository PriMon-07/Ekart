package com.infosys.ekart.model;

import java.util.Date;

public class SellerOrderDTO {
	private String userId;
	private long orderId;
	private String productName;
	private Integer quantity;
	private long totalPrice;
	private Date orderedDate;
	private Date deliverDate;
	private String orderStatus;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Date getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(Date deliverDate) {
		this.deliverDate = deliverDate;
	}
	public SellerOrderDTO(String userId, long orderId, String productName, Integer quantity, long totalPrice,
			Date orderedDate, Date deliverDate, String orderedStatus) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.productName = productName;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.orderedDate = orderedDate;
		this.deliverDate = deliverDate;
		this.orderStatus = orderedStatus;
	}
	public SellerOrderDTO() {
		super();
	}
	
}
