package com.infosys.ekart.model;

public class CardModel {
	private String cardNumber;
	private String expiryMonth;
	private String expiryYear;
	private String nameOnCard;
	public CardModel() {}
	public CardModel(String cardNumber, String expiryMonth, String expiryYear, String nameOnCard) {
		super();
		this.cardNumber = cardNumber;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.nameOnCard = nameOnCard;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((expiryMonth == null) ? 0 : expiryMonth.hashCode());
		result = prime * result + ((expiryYear == null) ? 0 : expiryYear.hashCode());
		result = prime * result + ((nameOnCard == null) ? 0 : nameOnCard.hashCode());
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
		CardModel other = (CardModel) obj;
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
		return true;
	}
	@Override
	public String toString() {
		return "CardModel [cardNumber=" + cardNumber + ", expiryMonth=" + expiryMonth + ", expiryYear=" + expiryYear
				+ ", nameOnCard=" + nameOnCard + "]";
	}
	
}
