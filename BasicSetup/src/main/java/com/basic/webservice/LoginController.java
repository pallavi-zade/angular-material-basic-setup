package com.basic.webservice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {
	final static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserManagementService userManagementService;
	@Value("${key}")
	String key;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Response> login(@RequestBody User user) throws Exception {
		logger.debug("******************* LoginController :: login method *******************");
		Response response = new Response();
		try {
			user = this.userManagementService.authenticate(user.getEmail(), user.getPassword());
			if (user != null) {
				logger.info("Inside LoginController :: login method ::User Found");
				response.setStatus(Status.SUCCESS);
				Map<String, Object> loginResponce = new HashMap<String, Object>();
				loginResponce.put("token", this.generateToken(Integer.toString(user.getId())));
				loginResponce.put("name", user.getFirstName()+" "+user.getLastName());
				response.setData(loginResponce);
			}
		} catch (BasicException e) {
			logger.error("Inside LoginController :: login method Exception::", e);
			response.setMessage(e.getMessage());
			response.setStatus(Status.ERROR);
		}catch (Exception e) {
			logger.error("Inside LoginController :: login method Exception::", e);
			response.setMessage(e.getMessage());
			response.setStatus(Status.ERROR);
		}
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Response> register(@RequestBody User user, HttpServletRequest request) {
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


	String generateToken(String userId) {
		logger.debug("Inside LoginController :: generateToken method **************");
		String token;
		// 2hrs

		token = Jwts.builder().setSubject(userId).claim("key", key).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS384, key).setExpiration(new Date(System.currentTimeMillis() + 7200000))
				.compact();
		System.out.println(token);
		return token;
	}
	
	

}
