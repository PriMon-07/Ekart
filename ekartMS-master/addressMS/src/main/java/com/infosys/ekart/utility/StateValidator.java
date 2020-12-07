package com.infosys.ekart.utility;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StateValidator implements ConstraintValidator<ValidState, String> {

	private List<String> states;
	
	@Override
	public void initialize(ValidState state) {
		states = new ArrayList<>();
		states.add("Andhra Pradesh");
		states.add("Arunachal Pradesh");
		states.add("Assam");
		states.add("Bihar");
		states.add("Chhattisgarh");
		states.add("Goa");
		states.add("Gujarat");
		states.add("Haryana");
		states.add("Himachal Pradesh");
		states.add("Jammu and Kashmir");
		states.add("Jharkhand");
		states.add("Karnataka");
		states.add("Kerala");
		states.add("Madhya Pradesh");
		states.add("Maharashtra");
		states.add("Manipur");
		states.add("Meghalaya");
		states.add("Mizoram");
		states.add("Nagaland");
		states.add("Odisha");
		states.add("Punjab");
		states.add("Rajasthan");
		states.add("Sikkim");
		states.add("Tamil Nadu");
		states.add("Telangana");
		states.add("Tripura");
		states.add("Uttar Pradesh");
		states.add("Uttarakhand");
		states.add("West Bengal");
	}

	@Override
  public boolean isValid(String state, ConstraintValidatorContext cxt) {
      if(states.contains(state)) {
    	  return true;
      }else {
    	  return false;
      }
  }

}