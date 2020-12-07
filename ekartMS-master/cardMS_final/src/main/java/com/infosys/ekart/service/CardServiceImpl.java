package com.infosys.ekart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.infosys.ekart.constants.ApplicationConstants;
import com.infosys.ekart.entity.CardEntity;
import com.infosys.ekart.exceptions.InvalidDetailsException;
import com.infosys.ekart.model.CardModel;
import com.infosys.ekart.repository.CardRepository;
import com.infosys.ekart.utils.CardUtils;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	CardRepository cardRepository;
	
	@Autowired
	CardUtils cardUtils;
	
	@Autowired
	AccountFeignClient accountFeignClient; 
	
	@Value("${EKART.INVALID_DETAILS}")
	private String invalidDetails;

	@Override
	public String addCards(String userid, CardModel card) throws Exception {
		if (!cardUtils.validateCardDetails(userid, card)) {
			throw new Exception(ApplicationConstants.ALL_FIELDS_ARE_REQUIRED);
		}
		Boolean validUserId = accountFeignClient.checkUser(userid);
		if (!validUserId) {
			throw new Exception(ApplicationConstants.INVALID_USER);
		}
		if (!cardUtils.validateCardNumber(card.getCardNumber())) {
			throw new Exception(ApplicationConstants.INVALID_CARD);
		}
		CardEntity cardEntity = cardRepository.findByUserIdAndCardNumber(userid, card.getCardNumber());
		if (null != cardEntity) {
			throw new Exception(ApplicationConstants.CARD_ALREADY_EXISTS);
		}
		try {
			if (!cardUtils.validateCardExpiry(card.getExpiryYear(), card.getExpiryMonth())) {
				throw new Exception(ApplicationConstants.CARD_EXPIRED);
			}
		} catch (Exception e) {
			throw new Exception(ApplicationConstants.INVALID_DATE);
		}
		cardEntity = cardUtils.parseCardModelAndUserId(userid, card);
		cardRepository.saveAndFlush(cardEntity);
		return ApplicationConstants.ADD_CARD_SUCCESS;
	}
	
	@Override
	public List<CardModel> getCardDetails(String userId) {

		List<CardModel> cardDetailsList = new ArrayList<>();
		List<CardEntity> cardEntityList = cardRepository.findByUserId(userId);
		try {
			for (CardEntity cardEntity : cardEntityList) {
				CardModel cardDetails = new CardModel();
				cardDetails.setCardNumber(cardEntity.getCardNumber());
				cardDetails.setExpiryMonth(cardEntity.getExpiryMonth());
				cardDetails.setExpiryYear(cardEntity.getExpiryYear());
				cardDetails.setNameOnCard(cardEntity.getNameOnCard());
				cardDetailsList.add(cardDetails);
			}
		} catch (Exception e) {
			return cardDetailsList;
		}
		return cardDetailsList;
	}

	@Override
	public Integer getCardId(CardModel cardDetails, String userId) throws InvalidDetailsException {

		List<CardEntity> cardEntityList = cardRepository.findByUserId(userId);
		for (CardEntity cardEntity : cardEntityList) {
			if (cardEntity.getCardNumber().equals(cardDetails.getCardNumber())
					&& cardEntity.getExpiryMonth().equals(cardDetails.getExpiryMonth())
					&& cardEntity.getExpiryYear().equals(cardDetails.getExpiryYear())
					&& cardEntity.getNameOnCard().equals(cardDetails.getNameOnCard())) {
				return cardEntity.getCardId();
			}
		}
		throw new InvalidDetailsException(invalidDetails);
	}

	@Override
	public boolean deleteCard(String cardNumber) {
		cardRepository.deleteByCardNumber(cardNumber);
		return true;
	}

}
