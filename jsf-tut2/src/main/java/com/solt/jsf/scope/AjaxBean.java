package com.solt.jsf.scope;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class AjaxBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Counter counter;

	public Counter getCounter() {
		return counter;
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	public void countUp() {
		counter.countUp();
	}
}
