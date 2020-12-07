package com.infosys.ekart.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADDRESS")
public class AddressEntity {

	private String userId;
	@Id
	private Integer addressId;
	private String address;
	private String city;
	private String state;
	private Integer pinCode;
	private long phoneNumber;
	

	public AddressEntity() {
		
	}
		
	public AddressEntity(String userId, Integer addressId, String address, String city, String state, Integer pinCode,
			long phoneNumber) {
		this.userId = userId;
		this.addressId = addressId;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.phoneNumber = phoneNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
