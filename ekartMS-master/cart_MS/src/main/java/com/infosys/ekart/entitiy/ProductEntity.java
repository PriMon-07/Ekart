/*added by dhanapriya*/
/*copied from productMS*/
package com.infosys.ekart.entitiy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	private String displayName;
	private String shortDesc;
	private String description;
	private String category;
	private Float price;
	private Float discount;
	private Float deliveryCharge;
	private String sellerId;

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Float getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(Float deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", displayName=" + displayName + ", shortDesc=" + shortDesc
				+ ", description=" + description + ", category=" + category + ", price=" + price + ", discount="
				+ discount + ", deliveryCharge=" + deliveryCharge + ", sellerId=" + sellerId + "]";
	}
}
