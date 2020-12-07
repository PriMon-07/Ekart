package com.infosys.ekart.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Ekart_Order")
@GenericGenerator(name="orderGen",strategy="increment")
public class OrderEntity {
	
	@Id
	@GeneratedValue(generator="orderGen")
	private Integer orderId;
	private String userId;
	private Integer addressId;
	private Integer cardId;
	private String productName;
	private String category;
	private String sellerName;
	private Float price;
	@Temporal(TemporalType.DATE)
	private Date orderedDate;
	@Temporal(TemporalType.DATE)
	private Date deliveryDate;
	private String orderStatus;
	private Integer quantity;
	
	
	public Date getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(Date orderdate) {
		this.orderedDate = orderdate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public String getproductName() {
		return productName;
	}
	public void setproductName(String displayname) {
		this.productName = displayname;
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
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	
	

}
