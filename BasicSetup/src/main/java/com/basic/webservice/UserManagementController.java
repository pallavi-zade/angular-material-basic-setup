package com.basic.webservice;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.basic.constant.SpringErrorCodes;
import com.basic.exception.BasicException;
import com.basic.model.User;
import com.basic.service.UserManagementService;
import com.basic.webservice.Response.Status;

@RestController
@RequestMapping("/user")
public class UserManagementController {

	final static Logger logger = LoggerFactory.getLogger(UserManagementController.class);
	@Autowired
	UserManagementService userManagementService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Response> add(@RequestBody User user, HttpServletRequest request) {
		logger.info("******************* UserManagementController :: add method *******************");
		Response response = new Response();
		try {
			user = this.userManagementService.addUser(user);
			if (user != null) {
				logger.info("User created successfully");
				response.setStatus(Status.SUCCESS);
				response.setMessage(SpringErrorCodes.CREATING_USER_MSG);
				response.setData(user);
			}
		} catch (BasicException e) {
			logger.error("Error in creating user. Exception:", e);
			response.setMessage(e.getMessage());
			response.setStatus(Status.ERROR);

		} catch (Exception e) {
			logger.error("Error in creating user. Exception:", e);
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<Response> getAll() {
		logger.info("******************* UserManagementController :: get all method *******************");
		Response response = new Response();
		List<User> userList = null;
		try {
			userList = this.userManagementService.getAll();
			response.setStatus(Status.SUCCESS);
			response.setData(userList);
		} catch (BasicException e) {
			logger.error("Error in getting user list. Exception:", e);
			response.setMessage(e.getMessage());
			response.setStatus(Status.ERROR);
			response.setData(userList);
		} catch (Exception e) {
			logger.error("Error in etting user list. Exception:", e);
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
