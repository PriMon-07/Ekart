package com.infosys.ekart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.ekart.constants.ApplicationConstants;
import com.infosys.ekart.entity.CardEntity;
import com.infosys.ekart.exception.InvalidDetailsException;
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

	@Override
	public String addCards(String userid, CardModel card) throws Exception {
		if (!cardUtils.validateCardDetails(userid, card)) {
			throw new InvalidDetailsException(ApplicationConstants.ALL_FIELDS_ARE_REQUIRED);
		}
		Boolean validUserId = accountFeignClient.checkUser(userid);
		if (!validUserId) {
			throw new InvalidDetailsException(ApplicationConstants.INVALID_USER);
		}
		if (!cardUtils.validateCardNumber(card.getCardNumber())) {
			throw new Exception(ApplicationConstants.INVALID_CARD);
		}
		CardEntity cardEntity = cardRepository.findByUserIdAndCardNumber(userid, card.getCardNumber());
		if (null != cardEntity) {
			throw new InvalidDetailsException(ApplicationConstants.CARD_ALREADY_EXISTS);
		}
		try {
			if (!cardUtils.validateCardExpiry(card.getExpiryYear(), card.getExpiryMonth())) {
				throw new InvalidDetailsException(ApplicationConstants.CARD_EXPIRED);
			}
		} catch (Exception e) {
			throw new InvalidDetailsException(ApplicationConstants.INVALID_DATE);
		}
		cardEntity = cardUtils.parseCardModelAndUserId(userid, card);
		cardRepository.saveAndFlush(cardEntity);
		return ApplicationConstants.ADD_CARD_SUCCESS;
	}

}
