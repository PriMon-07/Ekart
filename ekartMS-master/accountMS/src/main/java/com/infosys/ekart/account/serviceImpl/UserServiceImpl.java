package com.infosys.ekart.account.serviceImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.infosys.ekart.account.entity.UserEntity;
import com.infosys.ekart.account.exception.EmailAreadyUsedException;
import com.infosys.ekart.account.exception.ErrorOnServerException;
import com.infosys.ekart.account.exception.UserNotFoundException;
import com.infosys.ekart.account.model.User;
import com.infosys.ekart.account.repository.UserRepository;
import com.infosys.ekart.account.service.UserService;
import static com.infosys.ekart.account.constants.AccountConstants.USER_NOT_FOUND;
import static com.infosys.ekart.account.constants.AccountConstants.EMAIL_ALREADY_REGISTRED;
import static com.infosys.ekart.account.constants.AccountConstants.ERROR_ON_SERVER;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private Environment env;

	@Override
	public void addNewUser(User user) throws EmailAreadyUsedException, ErrorOnServerException {
		UserEntity entity = new UserEntity();
		try {
			BeanUtils.copyProperties(entity, user);
			Optional<UserEntity> isAvailable = repository.findById(user.getUserId());
			if (isAvailable.isPresent()) {
				throw new EmailAreadyUsedException(env.getProperty(EMAIL_ALREADY_REGISTRED));
			} else {
				repository.save(entity);
			}
		} catch (IllegalAccessException | InvocationTargetException | DataAccessException exception) {
			throw new ErrorOnServerException(env.getProperty(ERROR_ON_SERVER));
		}
	}

	@Override
	public void updateDetail(User user, String userId) throws ErrorOnServerException, UserNotFoundException {
		try {
			if (Objects.isNull(userId)) {
				throw new UserNotFoundException(env.getProperty(USER_NOT_FOUND));
			}
			Optional<UserEntity> isAvailable = repository.findById(userId);
			if (!isAvailable.isPresent()) {
				throw new UserNotFoundException(env.getProperty(USER_NOT_FOUND));
			} else {
				UserEntity userEntity = isAvailable.get();
				userEntity.setName(user.getName());
				userEntity.setPassword(user.getPassword());
				repository.save(userEntity);
			}
		} catch (DataAccessException exception) {
			throw new ErrorOnServerException(env.getProperty(ERROR_ON_SERVER));
		}
	}

	@Override
	public Boolean checkUser(String userId) throws ErrorOnServerException, UserNotFoundException {
		try {
			if (Objects.isNull(userId)) {
				return false;
			}
			Optional<UserEntity> isAvailable = repository.findById(userId);
			if (!isAvailable.isPresent()) {
				return false;
			}
			return true;
		} catch (DataAccessException exception) {
			throw new ErrorOnServerException(env.getProperty(ERROR_ON_SERVER));
		}
	}
}
