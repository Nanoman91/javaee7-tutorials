package com.solt.jsf.scope;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RequestBean {
	
	@Inject
	private Counter counter;

	public Counter getCounter() {
		return counter;
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	public String countUp() {
		counter.countUp();
		return "";
	}
}
