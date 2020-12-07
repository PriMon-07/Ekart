package com.infosys.ekart.service;

import com.infosys.ekart.model.CardModel;

public interface CardService {

	String addCards(String userid, CardModel card) throws Exception;

}
