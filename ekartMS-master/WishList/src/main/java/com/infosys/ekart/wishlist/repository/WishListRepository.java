package com.infosys.ekart.wishlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.ekart.wishlist.entity.WishListEntity;

@Repository
public interface WishListRepository extends JpaRepository<WishListEntity, Long> {

	WishListEntity findByUserIdAndProductName(String userId, String productName);

	List<WishListEntity> findAllByUserId(String userId);

	void deleteByWishListId(Long wishListId);

}
