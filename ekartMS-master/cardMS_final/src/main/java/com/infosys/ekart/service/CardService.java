package com.infosys.ekart.service;

import java.util.List;

import com.infosys.ekart.exceptions.InvalidDetailsException;
import com.infosys.ekart.model.CardModel;

public interface CardService {

	String addCards(String userid, CardModel card) throws Exception;

	public List<CardModel> getCardDetails(String userId);
	
	public Integer getCardId(CardModel cardDetails, String userId) throws InvalidDetailsException;

	public boolean deleteCard(String cardNumber);
}
