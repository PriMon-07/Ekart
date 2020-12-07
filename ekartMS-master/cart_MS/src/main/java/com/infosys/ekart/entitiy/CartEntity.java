package com.infosys.ekart.entitiy;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "cart")
public class CartEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // added by dhanapriya for oracle db. verify for mysql
	@Column(name = "cart_id")
	private Integer cartId;
	@Column(name = "cart_offer_price")
	private Float cartOfferPrice;
	private String category;
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "delivery_charge")
	private Float deliveryCharge;
	@Column(name = "display_name")
	private String displayName;
	private Float price;
	private Integer quantity;
	@Column(name = "seller_name")
	private String sellerName;
	private Float total;
	@Column(name = "user_id")
	private String userId;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Float getCartOfferPrice() {
		return cartOfferPrice;
	}

	public void setCartOfferPrice(Float cartOfferPrice) {
		this.cartOfferPrice = cartOfferPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(Float deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	public String getDisplayName() {
		return displayName;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
