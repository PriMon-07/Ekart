package com.infosys.ekart.api;

import static com.infosys.ekart.constant.AddressConstants.ADD_ADDRESS_URI;
import static com.infosys.ekart.constant.AddressConstants.VIEW_ADDRESS_TO_MODIFY_URI;
import static com.infosys.ekart.constant.AddressConstants.VIEW_ADDRESS_URI;
import static com.infosys.ekart.constant.AddressConstants.MODIFY_ADDRESS_URI;
import static com.infosys.ekart.constant.AddressConstants.DELETE_ADDRESS_URI;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ekart.exception.ErrorOnServerException;
import com.infosys.ekart.exception.InvalidUserIdException;
import com.infosys.ekart.exception.NoAddressFoundException;
import com.infosys.ekart.exception.UserNotLoggedInException;
import com.infosys.ekart.model.Address;
import static com.infosys.ekart.constant.AddressConstants.USER_NOT_LOGGED_IN;
import com.infosys.ekart.client.AuthenticationClient;
import com.infosys.ekart.service.AddressService;

@RestController
public class AddressAPI {

	@Autowired
	Environment env;

	@Autowired
	AddressService service;

	@Autowired
	private AuthenticationClient authenticationClient;

	@GetMapping(VIEW_ADDRESS_URI)
	public ResponseEntity<List<Address>> validateAddress(@PathVariable String userId)
			throws ErrorOnServerException, NoAddressFoundException, UserNotLoggedInException {
		Boolean isAuthenticated = authenticationClient.authenticateUser(userId);
		if (!isAuthenticated) {
			throw new UserNotLoggedInException(env.getProperty(USER_NOT_LOGGED_IN));
		}
		List<Address> address = service.getAddress(userId);
		return new ResponseEntity<List<Address>>(address, HttpStatus.OK);
	}

	@RequestMapping(value = ADD_ADDRESS_URI, method = RequestMethod.POST)
	public ResponseEntity<String> addAddress(@PathVariable String userid, @Valid @RequestBody Address address,
			BindingResult result) {
		if (result.hasErrors()) {
			String errors = "";
			for (ObjectError error : result.getAllErrors()) {
				errors += error.getDefaultMessage() + "\n";
			}
			return new ResponseEntity<String>(errors, HttpStatus.EXPECTATION_FAILED);
		} else {
			Boolean isAuthenticated = authenticationClient.authenticateUser(userid);
			if (!isAuthenticated) {
				try {
					throw new InvalidUserIdException(env.getProperty("INVALID_USER_ID"));
				} catch (InvalidUserIdException e) {
					return new ResponseEntity<String>(e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
				}
			}
			String resultText = "";
			try {
				resultText = service.saveAddress(userid, address);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
			}
			return new ResponseEntity<String>(resultText, HttpStatus.OK);

		}
	}

	@RequestMapping(value = VIEW_ADDRESS_TO_MODIFY_URI, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Object>> updateAddress(@PathVariable String userid, @PathVariable String addressid)
			throws InvalidUserIdException, UserNotLoggedInException {

		int addressId = 0;

		List<Object> returnList = new ArrayList<>();
		HttpStatus status = null;

		try {
			Boolean isAuthenticated = authenticationClient.authenticateUser(userid);
			if (!isAuthenticated) {
				throw new UserNotLoggedInException(env.getProperty(USER_NOT_LOGGED_IN));
			}
			addressId = Integer.parseInt(addressid);
			returnList.add(service.getAddress(addressId));
			if (returnList.isEmpty()) {
				throw new NoAddressFoundException(env.getProperty("NO_ADDRESS_FOUND"));
			}
			status = HttpStatus.OK;

		} catch (NumberFormatException | NoAddressFoundException e) {
			returnList.add(env.getProperty("INVALID_ADDRESS_ID"));
			status = HttpStatus.EXPECTATION_FAILED;

		}
		return new ResponseEntity<>(returnList, status);

	}

	@RequestMapping(value = MODIFY_ADDRESS_URI, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> getAddress(@PathVariable String userid, @PathVariable String addressid,
			@Valid @RequestBody Address address, BindingResult result) throws UserNotLoggedInException {

		if (result.hasErrors()) {
			String errors = "";
			for (ObjectError error : result.getAllErrors()) {
				errors += error.getDefaultMessage() + "\n";
			}
			return new ResponseEntity<String>(errors, HttpStatus.EXPECTATION_FAILED);
		}

		String message = null;
		HttpStatus status = null;

		try {
			Boolean isAuthenticated = authenticationClient.authenticateUser(userid);
			if (!isAuthenticated) {
				throw new UserNotLoggedInException(env.getProperty(USER_NOT_LOGGED_IN));
			}
			int addressId = Integer.parseInt(addressid);

			if (service.getAddress(addressId).equals(null)) {
				throw new NoAddressFoundException(env.getProperty("NO_ADDRESS_FOUND"));
			}

			message = service.updateAddress(address, addressId);

			status = HttpStatus.OK;

		} catch (NumberFormatException | NoAddressFoundException e) {

			message = e.getMessage();
			status = HttpStatus.EXPECTATION_FAILED;

		}
		return new ResponseEntity<>(message, status);
	}

	@RequestMapping(value = DELETE_ADDRESS_URI, method = RequestMethod.POST)
	public ResponseEntity<String> deleteAddress(@PathVariable String userid, @PathVariable String addressid)
			throws UserNotLoggedInException {

		String message = null;
		HttpStatus status = null;

		try {
			Boolean isAuthenticated = authenticationClient.authenticateUser(userid);
			if (!isAuthenticated) {
				throw new UserNotLoggedInException(env.getProperty(USER_NOT_LOGGED_IN));
			}

			int addressId = Integer.parseInt(addressid);

			if (service.getAddress(addressId).equals(null)) {
				throw new NoAddressFoundException(env.getProperty("NO_ADDRESS_FOUND"));
			}

			message = service.deleteAddress(addressId);

			status = HttpStatus.OK;

		} catch (NumberFormatException | NoAddressFoundException e) {

			message = e.getMessage();
			status = HttpStatus.EXPECTATION_FAILED;

		}
		return new ResponseEntity<>(message, status);

	}

}