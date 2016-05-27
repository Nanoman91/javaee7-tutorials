package com.solt.jsf;

import java.io.Serializable;

import javax.annotation.PostConstruct;

public class MyInterest implements Serializable{

	private static final long serialVersionUID = 1L;

	private Personal person;
	private Interest interest;
	
	@PostConstruct
	private void init() {
		person = new Personal();
		interest = new Interest();
	}
	
	public Personal getPerson() {
		return person;
	}
	public void setPerson(Personal person) {
		this.person = person;
	}
	public Interest getInterest() {
		return interest;
	}
	public void setInterest(Interest interest) {
		this.interest = interest;
	}
}
