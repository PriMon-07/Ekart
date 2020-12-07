package com.infosys.ekart.utils;

import java.util.Calendar;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.infosys.ekart.constants.ApplicationConstants;
import com.infosys.ekart.entity.CardEntity;
import com.infosys.ekart.model.CardModel;

@Component
public class CardUtils {

	public boolean validateCardDetails(String userid, CardModel card) {
		if (null != userid && null != card.getCardNumber() && null != card.getExpiryMonth()
				&& null != card.getExpiryYear() && null != card.getNameOnCard()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateCardNumber(String cardNumber) {
		Pattern card_pattern = Pattern.compile(ApplicationConstants.CARD_PATTERN);
		if (!card_pattern.matcher(cardNumber).matches()) {
			return false;
		}
		int[] ints = new int[cardNumber.length()];
		for (int i = 0; i < cardNumber.length(); i++) {
			ints[i] = Integer.parseInt(cardNumber.substring(i, i + 1));
		}
		for (int i = ints.length - 2; i >= 0; i = i - 2) {
			int j = ints[i];
			j = j * 2;
			if (j > 9) {
				j = j % 10 + 1;
			}
			ints[i] = j;
		}
		int sum = 0;
		for (int i = 0; i < ints.length; i++) {
			sum += ints[i];
		}
		return (sum % 10 == 0);
	}

	public boolean validateCardExpiry(String expiryYear, String expiryMonth) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR) - 2000;
		int currentMonth = (Calendar.getInstance().get(Calendar.MONTH)) + 1;
		int newYear = Integer.parseInt(expiryYear);
		int newMonth = Integer.parseInt(expiryMonth);
		if (newYear > currentYear) {
			return true;
		} else if (newYear == currentYear) {
			if (newMonth >= currentMonth) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public CardEntity parseCardModelAndUserId(String userid, CardModel card) {
		CardEntity cardEntity = new CardEntity();
		cardEntity.setCardNumber(card.getCardNumber());
		cardEntity.setExpiryMonth(card.getExpiryMonth());
		cardEntity.setExpiryYear(card.getExpiryYear());
		cardEntity.setNameOnCard(card.getNameOnCard());
		cardEntity.setUserId(userid);
		return cardEntity;
	}

}
