package com.infosys.ekart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.ekart.entity.CardEntity;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Integer> {
	public CardEntity findByUserIdAndCardNumber(String userId, String cardNumber);
}
