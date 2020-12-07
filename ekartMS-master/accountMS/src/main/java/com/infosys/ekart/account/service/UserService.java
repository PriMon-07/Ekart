package com.infosys.ekart.account.service;

import com.infosys.ekart.account.exception.EmailAreadyUsedException;
import com.infosys.ekart.account.exception.ErrorOnServerException;
import com.infosys.ekart.account.exception.UserNotFoundException;
import com.infosys.ekart.account.model.User;

public interface UserService {

	void addNewUser(User user) throws EmailAreadyUsedException, ErrorOnServerException;

	void updateDetail(User user, String userId) throws ErrorOnServerException, UserNotFoundException;

	Boolean checkUser(String userId) throws ErrorOnServerException, UserNotFoundException;

}
