package com.solt.jsf.scope;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SessBean implements Serializable {

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
