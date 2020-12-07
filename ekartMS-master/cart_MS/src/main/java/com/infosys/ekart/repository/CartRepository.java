package com.infosys.ekart.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.infosys.ekart.entitiy.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
	CartEntity findBySellerNameAndDisplayNameAndUserId(String sellerName, String displayName, String userId);

	List<CartEntity> findAll();

	List<CartEntity> findByUserId(String userId);

	List<CartEntity> findBySellerName(String sellerName);

	List<CartEntity> findBySellerNameAndDisplayName(String sellerName, String displayName);
}