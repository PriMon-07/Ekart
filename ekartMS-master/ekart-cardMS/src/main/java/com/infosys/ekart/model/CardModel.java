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
	public String toString() {
		return "CardModel [cardNumber=" + cardNumber + ", expiryMonth=" + expiryMonth + ", expiryYear=" + expiryYear
				+ ", nameOnCard=" + nameOnCard + "]";
	}
	
}
