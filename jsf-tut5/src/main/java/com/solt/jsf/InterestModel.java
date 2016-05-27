package com.solt.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@ApplicationScoped
public class InterestModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Interest> list;
	
	@PostConstruct
	private void init() {
		list = new ArrayList<>();
	}
	
	public void add(Interest i) {
		list.add(i);
	}
	
	@Named
	@Produces
	public List<Interest> getAllData() {
		return new ArrayList<>(list);
	}
	
	@Named
	@Produces
	public List<String> getSelectedInterests() {
		return Arrays.asList("Java Basic", "Java Web", "JavaFX", "Java EE");
	}
}
