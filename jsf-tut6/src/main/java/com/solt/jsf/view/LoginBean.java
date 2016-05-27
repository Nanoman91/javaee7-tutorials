package com.solt.jsf.view;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.solt.jsf.entity.User;
import com.solt.jsf.model.UserService;

@Model
public class LoginBean {

	@Inject
	private UserService model;
	
	public String signIn(User user, String targetView) {
		model.signIn(user);
		return String.format("/%s?faces-redirect=true", targetView);
	}
	
	public String signOut() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index?faces-redirect=true";
	}
}
