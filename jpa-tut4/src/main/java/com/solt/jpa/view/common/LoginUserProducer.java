package com.solt.jpa.view.common;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.solt.jpa.entity.User;
import com.solt.jpa.model.UserModel;

public class LoginUserProducer implements Serializable{

	private static final long serialVersionUID = 1L;

	@Named
	@LoginUser
	@Produces
	private User loginUser;
	@Inject
	private UserModel model;
	
	@PostConstruct
	private void init() {
		String loginId = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		if(null != loginId) {
			loginUser = model.getUser(loginId);
		}
	}

}
