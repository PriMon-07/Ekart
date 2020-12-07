package com.infosys.ekart.model;

public class DealsModel {
	private String displayName;
	private String shortDesc;
	private String category;
	private float discount;
	public DealsModel(){}
	public DealsModel(String displayName, String shortDesc, String category, float discount) {
		super();
		this.displayName = displayName;
		this.shortDesc = shortDesc;
		this.category = category;
		this.discount = discount;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "DealsModel [displayName=" + displayName + ", shortDesc=" + shortDesc + ", category=" + category
				+ ", discount=" + discount + "]";
	}
	
}
