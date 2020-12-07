package com.infosys.ekart.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.ekart.entity.CardEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Integer> {
	public CardEntity findByUserIdAndCardNumber(String userId, String cardNumber);
	
	List<CardEntity> findByUserId(String userId);

	CardEntity findByCardNumberAndExpiryMonthAndExpiryYearAndNameOnCard(String cardNumber, String expiryMonth,
			String expiryYear, String nameOnCard);
	
	@Transactional
	Integer deleteByCardNumber(String cardNumber);
}
