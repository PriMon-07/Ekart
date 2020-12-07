package com.infosys.ekart.model;

import java.util.Date;

public class Cart {
	
	private Integer cartId;
    private String displayName;
    private String sellerName;
    private Integer quantity;
    private String category;
    private String userId;
    private Float deliveryCharge;
    private Float total;
    private Float cartOfferPrice;
    private Float price;
    private Date date;
    
    private String message;
    
    public String getMessage() {
          return message;
    }
    public void setMessage(String message) {
          this.message = message;
    }
    public Integer getCartId() {
          return cartId;
    }
    public void setCartId(Integer cartId) {
          this.cartId = cartId;
    }
    public String getDisplayName() {
          return displayName;
    }
    public void setDisplayName(String displayName) {
          this.displayName = displayName;
    }
    public String getSellerName() {
          return sellerName;
    }
    public void setSellerName(String sellerName) {
          this.sellerName = sellerName;
    }
    public Integer getQuantity() {
          return quantity;
    }
    public void setQuantity(Integer quantity) {
          this.quantity = quantity;
    }
    public String getCategory() {
          return category;
    }
    public void setCategory(String category) {
          this.category = category;
    }
    public String getUserId() {
          return userId;
    }
    public void setUserId(String userId) {
          this.userId = userId;
    }
    public Float getDeliveryCharge() {
          return deliveryCharge;
    }
    public void setDeliveryCharge(Float deliveryCharge) {
          this.deliveryCharge = deliveryCharge;
    }
    public Float getTotal() {
          return total;
    }
    public void setTotal(Float total) {
          this.total = total;
    }
    public Float getCartOfferPrice() {
          return cartOfferPrice;
    }
    public void setCartOfferPrice(Float cartOfferPrice) {
          this.cartOfferPrice = cartOfferPrice;
    }
    public Float getPrice() {
          return price;
    }
    public void setPrice(Float price) {
          this.price = price;
    }
    public Date getDate() {
          return date;
    }
    public void setDate(Date date) {
          this.date = date;
    }


}
