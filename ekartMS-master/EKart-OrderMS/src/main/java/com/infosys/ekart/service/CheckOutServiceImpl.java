package com.infosys.ekart.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.ekart.entity.OrderEntity;
import com.infosys.ekart.model.Cart;
import com.infosys.ekart.model.CartValue;
import com.infosys.ekart.model.OrderDetails;
import com.infosys.ekart.model.ProductCart;
import com.infosys.ekart.model.ProductCartValue;
import com.infosys.ekart.model.ProductDetailsDTO;
import com.infosys.ekart.repository.OrderRepo;

@Service
public class CheckOutServiceImpl implements CheckOutService {
	
	@Autowired
	private OrderRepo repo;
	
	@Override
	public ProductCartValue checkout (CartValue cartValue) {
		List<Cart> productInCart = cartValue.getCartList();
		List<ProductCart> finalList = new ArrayList<>();
		Float totalPrice = 0.0f;
		for (Cart i : productInCart) {
			ProductCart product = new ProductCart();
			product.setCategory(i.getCategory());
			product.setDeliveryCharge(i.getDeliveryCharge());
			product.setProductName(i.getDisplayName());
			product.setQuantity(i.getQuantity());
			product.setSellerName(i.getSellerName());
			if (!(i.getCartOfferPrice()==null || i.getCartOfferPrice()==0.0f)) {
				product.setPrice(i.getCartOfferPrice());
				product.setTotal((i.getCartOfferPrice()*i.getQuantity()));
				
			}
			else {
				product.setPrice(i.getPrice());
				product.setTotal(i.getTotal());
			}
			totalPrice+=product.getTotal();
			finalList.add(product);
		}
		
		ProductCartValue productCartValue = new ProductCartValue();
		productCartValue.setProductlist(finalList);
		productCartValue.setTotalprice(totalPrice);
		return productCartValue;
	}

	@Override
	public String placeOrder(OrderDetails orederdetails, String userId) throws Exception {
		String status="";
		System.out.println(12);
		
		List<ProductCart> productList =  orederdetails.getOrderItems();
		if (Integer.parseInt(orederdetails.getCard().getExpiryYear())<LocalDate.now().getYear()) {
			System.out.println("here");
			throw new Exception("Your Card is expired");
		}
		if((Integer.parseInt(orederdetails.getCard().getExpiryYear())==LocalDate.now().getYear())&&(Integer.parseInt(orederdetails.getCard().getExpiryMonth())<LocalDate.now().getMonthValue())) {
			throw new Exception("Your Card is expired");
		}
		System.out.println(productList);
		for (ProductCart i : productList ) {
			
			OrderEntity entity = new OrderEntity();
			
			entity.setAddressId(orederdetails.getAddress().getAddressId());
			entity.setUserId(userId);
			entity.setCardId(orederdetails.getCard().getCardId());
			entity.setCategory(i.getCategory());
			entity.setproductName(i.getProductName());
			entity.setPrice(i.getPrice());
			entity.setOrderStatus("open");
			Date date1 = Date.valueOf(LocalDate.now());
			entity.setOrderedDate(date1);
			entity.setQuantity(i.getQuantity());
			entity.setSellerName(i.getSellerName());
			
			
			Integer orderId =repo.saveAndFlush(entity).getOrderId();
			status+=("OrderId "+orderId+" Product "+ i.getProductName() +"\n");
		}
		System.out.println(status);
		return status;
	}
	
	
	@Override
	public Set<String> getCatagories (String userId){
		Date date = Date.valueOf(LocalDate.now().minusMonths(1));
		List<OrderEntity> orderList= repo.findByUserIdAndOrderedDateGreaterThanEqual(userId, date);
		System.out.println(orderList.size());
		Set<String> catList = new HashSet<>();
		
		for (OrderEntity i : orderList) {
			catList.add(i.getCategory());
		}
		return catList;
	}
	
	//Ashish
		@Override
		public List<ProductDetailsDTO> getItemsSold(String sellerId) {
			return repo.getItemsSold(sellerId);
		}

}
