package com.jdc.book.view;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.jdc.book.entity.User;
import com.jdc.book.utils.LoginUser;

@Model
public class ProfileBean {

	@Inject
	@LoginUser
	private User user;
	
	public String update() {
		return "";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
