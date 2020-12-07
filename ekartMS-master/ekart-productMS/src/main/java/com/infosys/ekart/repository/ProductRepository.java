package com.infosys.ekart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.ekart.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
	List<ProductEntity> findByDisplayName(String productName);
	List<ProductEntity> findByDisplayNameLike(String name);
	List<ProductEntity> findByCategory(String sub);
	List<ProductEntity> findAllByDisplayNameAndSellerId(String productName, String sellerId);
	List<ProductEntity> findAllBySellerId(String sellerId);

	@Modifying(clearAutomatically = true)
	@Transactional(noRollbackFor = RuntimeException.class)
	@Query(value = "update ProductEntity set price=?2,discount=?3 where displayName=?1 AND sellerId=?4")
	void updatePriceAndDiscount(String displayName, Float price, Float discount, String sellerId);
}
