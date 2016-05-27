package com.solt.jsf.data;

import java.io.Serializable;

import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FlowScoped("regist")
public class RegistrationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserInterest interest;
	@Inject
	private DataModel model;

	public UserInterest getInterest() {
		return interest;
	}

	public void setInterest(UserInterest interest) {
		this.interest = interest;
	}
	
	public String save() {
		model.add(interest);
		return "/index";
	}

}
