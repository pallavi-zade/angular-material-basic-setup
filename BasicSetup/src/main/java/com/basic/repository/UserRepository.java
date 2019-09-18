package com.basic.repository;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.basic.model.User;

/**
 * @author PZade
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	public List<User> findAllByOrderByIdDesc();
	@Query("select u from User u where u.email = :email AND u.password = :password")
	User authenticateUser(@Param(value = "email") String email, @Param(value = "password") String password);
	}
