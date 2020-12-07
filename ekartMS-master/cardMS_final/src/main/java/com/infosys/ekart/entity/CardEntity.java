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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cardId ^ (cardId >>> 32));
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((expiryMonth == null) ? 0 : expiryMonth.hashCode());
		result = prime * result + ((expiryYear == null) ? 0 : expiryYear.hashCode());
		result = prime * result + ((nameOnCard == null) ? 0 : nameOnCard.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardEntity other = (CardEntity) obj;
		if (cardId != other.cardId)
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (expiryMonth == null) {
			if (other.expiryMonth != null)
				return false;
		} else if (!expiryMonth.equals(other.expiryMonth))
			return false;
		if (expiryYear == null) {
			if (other.expiryYear != null)
				return false;
		} else if (!expiryYear.equals(other.expiryYear))
			return false;
		if (nameOnCard == null) {
			if (other.nameOnCard != null)
				return false;
		} else if (!nameOnCard.equals(other.nameOnCard))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CardEntity [cardId=" + cardId + ", cardNumber=" + cardNumber + ", expiryMonth=" + expiryMonth
				+ ", expiryYear=" + expiryYear + ", nameOnCard=" + nameOnCard + ", userId=" + userId + "]";
	}
	
}
