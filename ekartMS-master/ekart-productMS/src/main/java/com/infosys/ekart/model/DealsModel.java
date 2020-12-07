package com.infosys.ekart.model;

import java.time.LocalDate;

import javax.persistence.Column;

import com.infosys.ekart.entity.Deals;

public class DealsModel {
	int dealId;	
	LocalDate fromdate;
	LocalDate todate;
	float discount; 
	String description;
	int productId;
	
	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	public int getDealId() {
		return dealId;
	}
	public void setDealId(int dealId) {
		this.dealId = dealId;
	}
	public LocalDate getFromdate() {
		return fromdate;
	}
	public void setFromdate(LocalDate fromdate) {
		this.fromdate = fromdate;
	}
	public LocalDate getTodate() {
		return todate;
	}
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setTodate(LocalDate todate) {
		this.todate = todate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DealsModel [dealId=" + dealId + ", fromdate=" + fromdate + ", todate=" + todate + ", discount="
				+ discount + ", description=" + description + "]";
	}

	public static DealsModel valueOf(Deals deals) {
		// TODO Auto-generated method stub
		DealsModel dealsModel = new DealsModel();
		dealsModel.setDealId(deals.getDealId());
		dealsModel.setDescription(deals.getDescription());
		dealsModel.setDiscount(deals.getDiscount());
		dealsModel.setFromdate(deals.getFromdate());
		dealsModel.setTodate(deals.getTodate());
		dealsModel.setProductId(deals.getProducts());
		return dealsModel;
	}

}
