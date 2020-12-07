package com.infosys.ekart.model;

import java.util.List;

public class CartValueModel {
	List<CartModel> cartList;
	private Float totalprice;
	private Float totaldeliverycharge;
	private Float grandtotal;
	
	public Float getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Float totalprice) {
		this.totalprice = totalprice;
	}
	public Float getTotaldeliverycharge() {
		return totaldeliverycharge;
	}
	public void setTotaldeliverycharge(Float totaldeliverycharge) {
		this.totaldeliverycharge = totaldeliverycharge;
	}
	public Float getGrandtotal() {
		return grandtotal;
	}
	public void setGrandtotal(Float grandtotal) {
		this.grandtotal = grandtotal;
	}
	public List<CartModel> getCartList() {
		return cartList;
	}
	public void setCartList(List<CartModel> cartList) {
		this.cartList = cartList;
	}
}
