package com.infosys.ekart.model;

import java.util.List;

public class ProductCartValue {
	
	private List<ProductCart> productlist;
	private Float totalprice;
	
	public List<ProductCart> getProductlist() {
		return productlist;
	}
	public void setProductlist(List<ProductCart> finalList) {
		this.productlist = finalList;
	}
	public Float getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Float totalprice) {
		this.totalprice = totalprice;
	}
	


}
