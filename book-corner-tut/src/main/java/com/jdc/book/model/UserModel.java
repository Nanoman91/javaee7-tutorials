package com.jdc.book.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.jdc.book.entity.User;
import com.jdc.book.utils.ShopException;

@ApplicationScoped
public class UserModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<User> users;
	
	@PostConstruct
	private void init() {
		users = Collections.synchronizedList(new ArrayList<>());
	}
	
	public User login(String login, String pass) {
		User u = findById(login);
		if(null == u) {
			throw new ShopException("Please check login ID");
		}
		
		if(!u.getPassword().equals(pass)) {
			throw new ShopException("Please confirm your password");
		}
		
		return u;
	}
	
	public User signUp(String name, String login, String pass) {
		User u = findById(login);
		if(null != u) {
			throw new ShopException("Your login id has been already used!");
		}
		
		u = new User();
		u.setName(name);
		u.setPassword(pass);
		u.setLoginId(login);
		
		users.add(u);
		
		return u;
	}
	
	private User findById(String login) {
		return users.stream().filter(a -> a.getLoginId().equals(login)).findFirst().orElse(null);
	}
	

}
