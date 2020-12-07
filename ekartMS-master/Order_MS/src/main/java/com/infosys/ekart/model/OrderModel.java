package com.infosys.ekart.model;

import java.util.Date;

public class OrderModel {
	private long orderId;
	private String productName;
	private String category;
	private String sellerName;
	private Integer price;
	private Integer quantity;
	private Integer totalPrice;
	private Date orderedDate;
	private String orderStatus;
	private boolean btnReviewProduct;
	private boolean btnReviewSeller;
	private boolean btnCancel;
	private boolean btnReturn;
	
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
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
	public boolean isBtnReviewProduct() {
		return btnReviewProduct;
	}
	public void setBtnReviewProduct(boolean btnReviewProduct) {
		this.btnReviewProduct = btnReviewProduct;
	}
	public boolean isBtnReviewSeller() {
		return btnReviewSeller;
	}
	public void setBtnReviewSeller(boolean btnReviewSeller) {
		this.btnReviewSeller = btnReviewSeller;
	}
	public boolean isBtnCancel() {
		return btnCancel;
	}
	public void setBtnCancel(boolean btnCancel) {
		this.btnCancel = btnCancel;
	}
	public boolean isBtnReturn() {
		return btnReturn;
	}
	public void setBtnReturn(boolean btnReturn) {
		this.btnReturn = btnReturn;
	}

	
}
