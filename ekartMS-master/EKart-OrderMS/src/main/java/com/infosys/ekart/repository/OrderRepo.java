package com.infosys.ekart.repository;



import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infosys.ekart.entity.OrderEntity;
import com.infosys.ekart.model.ProductDetailsDTO;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {
	
	List<OrderEntity> findByUserIdAndOrderedDateGreaterThanEqual(String userid,Date date);
	
	@Query(value="SELECT " +
	           "new com.infosys.ekart.model.ProductDetailsDTO(SUM(o.quantity), SUM(o.price*o.quantity),o.productName,o.category) " +
	           "FROM " +
	           "OrderEntity o where orderStatus='delivered' and o.sellerName=:sellerId " +
	           "GROUP BY " +
	           "o.productName,o.category")
	List<ProductDetailsDTO> getItemsSold(@Param("sellerId")String sellerId);

}
