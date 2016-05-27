package com.jdc.book.view;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.jdc.book.entity.User;
import com.jdc.book.model.UserModel;
import com.jdc.book.utils.MessageUtils;
import com.jdc.book.utils.SecurityInfo;
import com.jdc.book.utils.ShopException;

@Model
public class LoginBean {
	
	private String name;
	private String login;
	private String pass;
	
	@Inject
	private UserModel model;
	@Inject
	private SecurityInfo security;
	
	public String login() {
		
		try {
			User u = model.login(login, pass);
			security.setUser(u);
		} catch (ShopException e) {
			MessageUtils.addMessage(e.getMessage());
			return "/index";
		}
		return "/index?faces-redirect=true";
	}
	
	public String signUp() {
		
		try {
			User u = model.signUp(name, login, pass);
			security.setUser(u);
		} catch (ShopException e) {
			MessageUtils.addMessage(e.getMessage());
			return "/index";
		}
		return "/index?faces-redirect=true";
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index?faces-redirect=true";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
