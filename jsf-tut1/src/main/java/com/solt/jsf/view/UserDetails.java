package com.solt.jsf.view;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.solt.jsf.model.User;
import com.solt.jsf.model.UserModel;

@Model
public class UserDetails {
	
	private User user;
	@Inject
	private UserModel model;

	@PostConstruct
	private void init() {
		String id = FacesContext.getCurrentInstance()
					.getExternalContext()
					.getRequestParameterMap().get("id");
		user = model.findById(Integer.parseInt(id));
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
