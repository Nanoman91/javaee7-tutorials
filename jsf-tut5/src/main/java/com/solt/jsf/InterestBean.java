package com.solt.jsf;

import java.io.Serializable;

import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FlowScoped("regist")
public class InterestBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Inject
	private Interest interests;
	@Inject
	private InterestModel model;

	public Interest getInterests() {
		return interests;
	}

	public void setInterests(Interest interests) {
		this.interests = interests;
	}
	
	public String save() {
		model.add(interests);
		return "success";
	}

}
