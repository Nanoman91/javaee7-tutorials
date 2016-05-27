package com.solt.jsf.scope;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class AppBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
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
