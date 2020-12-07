package com.infosys.ekart.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.infosys.ekart.entitiy.OrderEntity;

@Repository 
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {			
	@Query("select order from OrderEntity order where order.userId =:userId")
	List<OrderEntity> findAllByUserId(@Param("userId") String userId);
	
//	@Query("select order from OrderEntity order where order.userId =:userId and order.orderId =:orderId")
	OrderEntity findByUserIdAndOrderId(String userId, long orderId);
	
	OrderEntity findByOrderId(long orderId); //sanket
	
	@Query("select order from OrderEntity order where order.sellerName =?1")
	List<OrderEntity> findBySellerName(String sellerName);  //babita
}