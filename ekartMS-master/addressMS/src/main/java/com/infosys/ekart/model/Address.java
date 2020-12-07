package com.infosys.ekart.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

import com.infosys.ekart.entity.AddressEntity;
import com.infosys.ekart.utility.ValidState;

@Component
public class Address {
	
	@NotNull
	private String address;
	
	@NotNull
	@Pattern(regexp = "^[A-Za-z ]+$", message = "City should not contain any special characters except space")
	private String city;
	
	@NotNull
	@ValidState(message = "Invalid State")
	private String state;
	
	@NotNull
	@Min(value = 100000, message = "Pincode should be a 6 digit number")
	@Max(value = 999999, message = "Pincode should be a 6 digit number")
	private Integer pinCode;
	
	@NotNull
	@Min(value = 1000000000l, message = "Phone Number should be a 10 digit number")
	@Max(value = 9999999999l, message = "Phone Number should be a 10 digit number")
	private Long phoneNumber;

	public Address() {
		
	}
	
	public Address(String address, String city, String state, Integer pinCode, Long phoneNumber) {
		this.address = address;
		this.city = city;
		this.state=state;
		this.pinCode = pinCode;
		this.phoneNumber = phoneNumber;
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

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public AddressEntity toAddressEntity() {
		AddressEntity entity = new AddressEntity(null,null,this.getAddress(),this.getCity(),this.getState(), this.getPinCode(),this.getPhoneNumber());
		return entity;
	}

}
