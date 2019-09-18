package com.basic.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.basic.exception.BasicException;
import com.basic.model.User;


/**
 * @author PZade
 *
 */
@Component
public interface UserManagementService {
	public User addUser(User user) throws Exception;
	public List<User> getAll() throws BasicException;
	public User authenticate(String email, String password) throws Exception;
}
