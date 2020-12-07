package com.infosys.ekart.account.api;

import static com.infosys.ekart.account.constants.AccountConstants.SIGNUP_URI;
import static com.infosys.ekart.account.constants.AccountConstants.UPDATE_DETAIL_URI;
import static com.infosys.ekart.account.constants.AccountConstants.VALIDATE_USER_URI;
import static com.infosys.ekart.account.constants.AccountConstants.USER_NOT_LOGGED_IN;
import static com.infosys.ekart.account.constants.AccountConstants.USER_REGISTRED_SUCCESSFULLY;
import static com.infosys.ekart.account.constants.AccountConstants.DETAIL_UPDATED_SUCCESSFULLY;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ekart.account.exception.EmailAreadyUsedException;
import com.infosys.ekart.account.exception.ErrorOnServerException;
import com.infosys.ekart.account.exception.UserNotFoundException;
import com.infosys.ekart.account.model.BaseResponse;
import com.infosys.ekart.account.model.User;
import com.infosys.ekart.account.serviceImpl.UserServiceImpl;
import com.infosys.ekart.account.exception.UserNotLoggedInException;

@RestController
@CrossOrigin
public class AccountAPI {
	@Autowired
	private UserServiceImpl service;

	@Autowired
	Environment env;

	@PostMapping(SIGNUP_URI)
	public ResponseEntity<BaseResponse> addNewUser(@Valid @RequestBody User user)
			throws EmailAreadyUsedException, ErrorOnServerException {
		service.addNewUser(user);
		BaseResponse response = new BaseResponse();
		response.setMessage(env.getProperty(USER_REGISTRED_SUCCESSFULLY));
		response.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@PostMapping(UPDATE_DETAIL_URI)
	public ResponseEntity<BaseResponse> updateDetail(@Valid @RequestBody User user, @PathVariable String userId)
			throws ErrorOnServerException, UserNotFoundException, UserNotLoggedInException {
		Boolean isAuthenticated = service.checkUser(userId);
		if (!isAuthenticated) {
			throw new UserNotLoggedInException(env.getProperty(USER_NOT_LOGGED_IN));
		}
		service.updateDetail(user, userId);
		BaseResponse response = new BaseResponse();
		response.setMessage(env.getProperty(DETAIL_UPDATED_SUCCESSFULLY));
		response.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@GetMapping(VALIDATE_USER_URI)
	public ResponseEntity<Boolean> checkUser(@PathVariable String userId)
			throws ErrorOnServerException, UserNotFoundException {
		Boolean isVerified = service.checkUser(userId);
		return new ResponseEntity<Boolean>(isVerified, HttpStatus.OK);
	}

}