package com.infosys.ekart.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.infosys.ekart.model.DealsModel;

@Entity
@Table(name = "deals")
public class Deals {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int dealId;
	
	@Column(name="fromdate")
	LocalDate fromdate;
	
	@Column(name="todate")
	LocalDate todate;
	
	@Column(name="discount")
	float discount; 
	
	@Column(name="description")
	String description;
	
	@Column(name = "products")
	int productId;

	public int getDealId() {
		return dealId;
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

	public void setTodate(LocalDate todate) {
		this.todate = todate;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProducts() {
		return productId;
	}

	public void setProducts(int products) {
		this.productId = products;
	}	
	

}
