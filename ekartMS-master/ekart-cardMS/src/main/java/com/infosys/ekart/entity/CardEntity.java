package com.infosys.ekart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="card_details")
public class CardEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="card_id")
	private Integer cardId;
	@Column(name="card_number")
	private String cardNumber;
	@Column(name="expiry_month")
	private String expiryMonth;
	@Column(name="expiry_year")
	private String expiryYear;
	@Column(name="name_on_card")
	private String nameOnCard;
	@Column(name="user_id")
	private String userId;
	
	public CardEntity() {}
	public CardEntity(Integer cardId, String cardNumber, String expiryMonth, String expiryYear, String nameOnCard,
			String userId) {
		super();
		this.cardId = cardId;
		this.cardNumber = cardNumber;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.nameOnCard = nameOnCard;
		this.userId = userId;
	}
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public String getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "CardEntity [cardId=" + cardId + ", cardNumber=" + cardNumber + ", expiryMonth=" + expiryMonth
				+ ", expiryYear=" + expiryYear + ", nameOnCard=" + nameOnCard + ", userId=" + userId + "]";
	}
	
}
