package com.basic.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.basic.constant.SpringErrorCodes;
import com.basic.exception.BasicException;
import com.basic.model.User;
import com.basic.repository.UserRepository;

/**
 * @author PZade
 *
 */
@Service
public class UserManagementServiceImpl implements UserManagementService {

	final static Logger logger = LoggerFactory.getLogger(UserManagementServiceImpl.class);

	@Autowired
	UserRepository userRepository;

	public User addUser(User user) throws BasicException {
		try {
			user=this.userRepository.save(user);
		} catch (Exception e) {
			if (e instanceof DataIntegrityViolationException ) {
				logger.error("Error in creating user. Exception:", e);
				throw new BasicException(SpringErrorCodes.CREATE_ERROR_CODE,
						SpringErrorCodes.ERROR_DUPLICATE_EMAIL_MSG);
			} else {
				logger.error("Error in creating user. Exception:", e);
				throw new BasicException(SpringErrorCodes.CREATE_ERROR_CODE,
						SpringErrorCodes.SERVER_ERROR);
			}
		}
		return user;
	}

	public User authenticate(String email, String password) throws BasicException {
		try {
			
			User user = this.userRepository.authenticateUser(email, password);
			
			if (user == null) {
				logger.debug(SpringErrorCodes.LOGIN_ERROR_INVALID_CREDIANLS_MSG);
				throw new BasicException(SpringErrorCodes.LOGIN_ERROR_CODE,
						SpringErrorCodes.LOGIN_ERROR_INVALID_CREDIANLS_MSG);
			} 
			return user;
		} catch (BasicException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Error in authenticate. Exception:", e);
			throw new BasicException(SpringErrorCodes.LOGIN_ERROR_CODE, SpringErrorCodes.SERVER_ERROR);
		}
	}


	@Override
	public List<User> getAll() throws BasicException {
		List<User> userList = (List<User>) this.userRepository.findAllByOrderByIdDesc();
		if (userList == null) {
			logger.debug("NO data found");
			throw new BasicException(SpringErrorCodes.GET_DATA_ERROR_CODE,
					SpringErrorCodes.NO_DATA_FOUND_MSG);
		}
		return userList;
	}
}
