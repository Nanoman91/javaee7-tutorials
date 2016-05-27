package com.solt.jsf.model;

import javax.ejb.Local;

import com.solt.jsf.entity.User;

@Local
public interface UserService {

	void add(User user);

	void signIn(User user);
	
}