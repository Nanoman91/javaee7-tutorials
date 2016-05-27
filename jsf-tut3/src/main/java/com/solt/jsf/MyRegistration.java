package com.solt.jsf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@FlowScoped(value="registration")
public class MyRegistration implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private InterestDataModel model;

	@MemoryModel
	@Inject
	private MyInterest data;
	
	@PostConstruct
	private void init() {
		System.out.println("Flow Start");
	}

	public InterestDataModel getModel() {
		return model;
	}

	public void setModel(InterestDataModel model) {
		this.model = model;
	}

	public MyInterest getData() {
		return data;
	}

	public void setData(MyInterest data) {
		this.data = data;
	}

	public String save() {
		model.add(data);
		return "flowEnd";
	}
	
	
}
