package com.solt.jsf.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.solt.jsf.model.User;
import com.solt.jsf.model.UserModel;

@Model
public class UserList {
	
	private List<User> list;
	
	@Inject
	private UserModel model;
	
	@PostConstruct
	public void init() {
		
		String id = getParam("id");
		String action = getParam("action");
		
		if(null != id && null != action) {
			model.delete(id);
		}
		
		list = model.getAllUser();
	}

	private String getParam(String string) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(string);
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

}
