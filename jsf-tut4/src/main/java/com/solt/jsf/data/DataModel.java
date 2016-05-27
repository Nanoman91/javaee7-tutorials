package com.solt.jsf.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@ApplicationScoped
public class DataModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<UserInterest> data;
	
	@PostConstruct
	private void init() {
		data = new ArrayList<>();
	}
	
	public void add(UserInterest u) {
		data.add(u);
	}
	
	@Named
	@Produces
	public List<UserInterest> getAllData() {
		return new ArrayList<>(data);
	}
	
	@Named
	@Produces
	public List<String> getSelectData() {
		return Arrays.asList("Internet", "Java", "Java EE", "JavaFX");
	}
}
