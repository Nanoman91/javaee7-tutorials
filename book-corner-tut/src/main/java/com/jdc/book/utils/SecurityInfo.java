package com.jdc.book.utils;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.jdc.book.entity.User;

import java.io.Serializable;

@Named
@SessionScoped
public class SecurityInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@LoginUser
	@Produces
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean isLogin() {
		return null != user;
	}
	
	public boolean isNoContact() {
		if(null != user) {
			return null == user.getPhone() ||
					user.getPhone().isEmpty() ||
					null == user.getEmail() ||
					user.getEmail().isEmpty() ||
					null == user.getAddress() ||
					user.getAddress().isEmpty();
		}
		
		return true;
	}

}
