package com.infosys.ekart.service;

import java.util.List;

import com.infosys.ekart.entity.AddressEntity;
import com.infosys.ekart.exception.ErrorOnServerException;
import com.infosys.ekart.exception.NoAddressFoundException;
import com.infosys.ekart.model.Address;

public interface AddressService {

	List<Address> getAddress(String userID) throws ErrorOnServerException, NoAddressFoundException;
	
	String saveAddress(String userId, Address address);

	AddressEntity getAddress(Integer addressId);
	
	boolean isValidUserId(String userid);

	String updateAddress(Address address, Integer addressId);

	String deleteAddress(int addressId);

}
