package com.infosys.ekart.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.ekart.entitiy.CartEntity;
import com.infosys.ekart.exception.ModifyCartException;
import com.infosys.ekart.exception.QuantityValueExceededException;
import com.infosys.ekart.model.CartDTO;
import com.infosys.ekart.model.CartModel;
import com.infosys.ekart.model.CartValueModel;
import com.infosys.ekart.model.NotificationModel;
import com.infosys.ekart.repository.CartRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartRepository cartRepo;

	public CartValueModel viewCart(String userId) {

		List<CartEntity> cartList = cartRepository.findByUserId(userId);

		List<CartModel> cartListModel = new ArrayList<CartModel>();
		CartValueModel cartValueModel = new CartValueModel();

		Float grandtotal = 0.0f;
		Float totaldeliverycharge = 0.0f;
		Float totalprice = 0.0f;

		for (CartEntity cartItem : cartList) {
			totalprice += cartItem.getCartOfferPrice() * cartItem.getQuantity();
			totaldeliverycharge += cartItem.getDeliveryCharge();

			CartModel cm = new CartModel();
			cm.setCartId(cartItem.getCartId());
			cm.setCartOfferPrice(cartItem.getCartOfferPrice());
			cm.setCategory(cartItem.getCategory());
			cm.setDate(cartItem.getDate());
			cm.setDeliveryCharge(cartItem.getDeliveryCharge());
			cm.setDisplayName(cartItem.getDisplayName());
			cm.setPrice(cartItem.getPrice());
			cm.setQuantity(cartItem.getQuantity());
			cm.setSellerName(cartItem.getSellerName());
			cm.setTotal(cartItem.getTotal());
			cm.setUserId(cartItem.getUserId());

			cartListModel.add(cm);
		}
		if (totalprice > 1000) {
			totaldeliverycharge = 0f;
			grandtotal = totalprice;
		} else {
			grandtotal = totalprice + totaldeliverycharge;
		}
		cartValueModel.setCartList(cartListModel);
		cartValueModel.setGrandtotal(grandtotal);
		cartValueModel.setTotaldeliverycharge(totaldeliverycharge);
		cartValueModel.setTotalprice(totalprice);

		return cartValueModel;
	}

	/* added by dhanapriya */
	/*US08*/
	public String addToCart(CartDTO cartDTO, String userId) throws QuantityValueExceededException {
		CartEntity cartEntity = cartRepository.findBySellerNameAndDisplayNameAndUserId(cartDTO.getSellerName(),
				cartDTO.getDisplayName(), userId);
		CartDTO cart = cartDTO;
		String guestId = "";
		if (cartEntity == null) {
			try {
				if (userId.equalsIgnoreCase("guest")) {
					// guest id random generation
					guestId = generateGuestId();
					userId = guestId;
					cart.setUserId(guestId);
				} else {
					cart.setUserId(userId);
				}

				if (cart.getQuantity() > 4) {
					throw new QuantityValueExceededException("Invalid.quantity");
				}

				Calendar currenttime = Calendar.getInstance();
				Date date = new Date((currenttime.getTime()).getTime());
				cart.setDate1(date);

				CartEntity newCartEntity = new CartEntity();
				newCartEntity.setCategory(cart.getCategory());
				newCartEntity.setCartOfferPrice(0.0f);
				newCartEntity.setDisplayName(cart.getDisplayName());
				newCartEntity.setQuantity(cart.getQuantity());
				newCartEntity.setUserId(cart.getUserId());
				newCartEntity.setSellerName(cart.getSellerName());
				newCartEntity.setDate(cart.getDate1());
				newCartEntity.setDeliveryCharge(cart.getDeliveryCharge());
				newCartEntity.setPrice(cart.getPrice());
				newCartEntity.setTotal((cart.getPrice() * cart.getQuantity()) + cart.getDeliveryCharge());

				cartRepository.saveAndFlush(newCartEntity);
			} catch (Exception e) {
				throw e;
			}
		} else {
			int quantity = cartEntity.getQuantity() + cartDTO.getQuantity();
			if (quantity > 4) {
				throw new QuantityValueExceededException("Invalid.quantity");
			}
			try {
				cartEntity.setQuantity(quantity);
				cartEntity.setTotal((cart.getPrice() * cart.getQuantity()) + cart.getDeliveryCharge());
				Calendar currenttime = Calendar.getInstance();
				Date date = new Date((currenttime.getTime()).getTime());
				cartEntity.setDate(date);
				cartRepository.saveAndFlush(cartEntity);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		return userId;
	}
	
	/*US09*/
	public String modifyCart(CartDTO cartDTO, String userId)
			throws ModifyCartException, QuantityValueExceededException {
		CartEntity centity = cartRepository.findBySellerNameAndDisplayNameAndUserId(cartDTO.getSellerName(),
				cartDTO.getDisplayName(), userId);
		if (centity != null) {
			if (cartDTO.getQuantity() > 4) {
				throw new QuantityValueExceededException("Invalid.quantity");
			}
			if (cartDTO.getQuantity() == 0) {
				try {
					cartRepository.delete(centity);
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			} else {
				centity.setQuantity(cartDTO.getQuantity());
				centity.setCartOfferPrice(cartDTO.getCartOfferPrice());
				centity.setTotal((cartDTO.getCartOfferPrice() * cartDTO.getQuantity()) + cartDTO.getDeliveryCharge());

			}

			try {
				cartRepository.save(centity);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		} else {
			throw new ModifyCartException("Invalid.cartDetails");
		}
		return "modify.success";
	}
	
	public String updateGuestId(String guestId, String userId) throws Exception {
		try {
			List<CartEntity> cartEntityList = cartRepository.findByUserId(userId);
			if(cartEntityList.isEmpty() || cartEntityList == null) {
				throw new Exception("Cart.Empty");
			}
			for(CartEntity ce : cartEntityList) {
				ce.setUserId(userId);
				cartRepository.save(ce);
			}
		}
		catch(Exception e) {
			throw e;
		}
		return "UpdateGuestId.Success";
	}
	
	/*Guest Id random generation for US08*/
	public String generateGuestId() {
		String IDCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder guest = new StringBuilder();
		Random random = new Random();
		while (guest.length() < 10) { // length of the random string.
			int index = (int) (random.nextFloat() * IDCHARS.length());
			guest.append(IDCHARS.charAt(index));
		}
		return guest.toString();
	}
	/*--------------------------------*/
	
	@Override
	public Integer viewCartCount(String userId) {

		logger.info("In viewCartCount service");

		List<CartEntity> countList = cartRepo.findByUserId(userId);
		return countList.size();
	}

	public List<CartDTO> searchCartSeller(String sellerName) {

		logger.info("In searchCartSeller service");

		List<CartEntity> countList = cartRepo.findBySellerName(sellerName);
		List<CartDTO> cartList = new ArrayList<CartDTO>();
		CartDTO cdto;
		for (CartEntity e : countList) {
			cdto = CartDTO.toCartDTO(e);
			cartList.add(cdto);
		}

		return cartList;

	}
	@Override
	public boolean updateOfferPrice(CartDTO cartDTO) {

		logger.info("In updateOfferPrice service");

		List<CartEntity> cartList = cartRepo.findBySellerNameAndDisplayName(cartDTO.getSellerName(), cartDTO.getProductName());
		System.out.println();
		for (CartEntity cart : cartList) {
			System.out.println(cart.getSellerName());
			if (cart != null) {
				if (cartDTO.getCartOfferPrice() <= cart.getPrice()) {
					cart.setCartOfferPrice(cartDTO.getCartOfferPrice());
				}
				try {
					cartRepo.saveAndFlush(cart);
					String userId = cart.getUserId();
					System.out.println("userId"+userId);
					NotificationModel notificationModel = new NotificationModel();
					notificationModel.setUserId(userId);
					notificationModel.setMessage(
							"The product " +  cartDTO.getProductName() + "'s has been modified to" + cartDTO.getCartOfferPrice().toString());
					notificationModel.setMessageType("CartOffer");
					String status1 = new RestTemplate().postForObject("http://localhost:5002/notifications/add", notificationModel, String.class);

				}
				catch(Exception error ) {
					System.out.println("Error while Adding " + error);
					return false;
				}
			}

		}
		return true;

	}

}
