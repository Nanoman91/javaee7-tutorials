package com.solt.jsf;

import java.io.Serializable;
import java.util.Set;

public class Interest implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Set<String> interest;
	private String comment;
	
	public Set<String> getInterest() {
		return interest;
	}
	public void setInterest(Set<String> interest) {
		this.interest = interest;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
