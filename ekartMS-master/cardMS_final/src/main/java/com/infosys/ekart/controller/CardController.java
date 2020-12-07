package com.infosys.ekart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ekart.client.AuthenticationClient;
import com.infosys.ekart.constants.ApplicationConstants;
import com.infosys.ekart.exceptions.InvalidDetailsException;
import com.infosys.ekart.exceptions.UserNotLoggedInException;
import com.infosys.ekart.model.CardModel;
import com.infosys.ekart.service.CardService;

@RestController
public class CardController {
	@Autowired
	CardService cardService;
	
	@Autowired
	Environment env;
	
	@Autowired
	private AuthenticationClient authenticationClient;
	
	@PostMapping(value = ApplicationConstants.ADD_CARD_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> addCards(@PathVariable("userid") String userid, @RequestBody CardModel card) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			map.put(ApplicationConstants.DATA, cardService.addCards(userid,card));
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			map.put(ApplicationConstants.DATA, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}
	
	@RequestMapping(value = ApplicationConstants.CARD_CONTROLLER, method = RequestMethod.GET)
	public ResponseEntity<List<CardModel>> displayCards(@PathVariable String userId) {
		List<CardModel> cardDetailsList = null;
		HttpStatus status = HttpStatus.OK;
		try {
			cardDetailsList = cardService.getCardDetails(userId);
		} catch (Exception e) {
			status = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<CardModel>>(cardDetailsList, status);
	}

	@RequestMapping(value = ApplicationConstants.CARD_CONTROLLER_CARDID, method = RequestMethod.POST)
	public Integer getCardId(@RequestBody CardModel cardDetails, @PathVariable String userId)
			throws InvalidDetailsException {
		return cardService.getCardId(cardDetails, userId);
	}
	
	@RequestMapping(value = ApplicationConstants.DELETE_CARD_URL, method = RequestMethod.POST)
	public ResponseEntity<String> deleteCardInformation(@PathVariable String userid, @PathVariable String cardnumber) throws UserNotLoggedInException{
		
		Boolean isAuthenticated = authenticationClient.authenticateUser(userid);
		if (!isAuthenticated) {
			throw new UserNotLoggedInException(env.getProperty("USER_NOT_LOGGED_IN"));
		}
		String returnText = "";
		HttpStatus status = null;
		if(cardService.deleteCard(cardnumber)) {
			returnText = env.getProperty("DELETED_SUCCESSFULLY");
			status = HttpStatus.OK;
		}else {
			returnText = env.getProperty("INTERNAL_SERVER_ERROR");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(returnText, status);
		
	}
}
