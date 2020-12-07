package com.infosys.ekart.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ekart.constants.ApplicationConstants;
import com.infosys.ekart.model.CardModel;
import com.infosys.ekart.service.CardService;

@RestController
public class CardController {
	@Autowired
	CardService cardService;
	
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
}
