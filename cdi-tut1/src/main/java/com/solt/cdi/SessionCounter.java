package com.solt.cdi;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@CounterType(CounterTypes.Session)
@SessionScoped
public class SessionCounter implements Counter, Serializable{

	private static final long serialVersionUID = 1L;
	
	private int i;

	@Override
	public void countUp() {
		i++;
	}

	@Override
	public int getCount() {
		return i;
	}

}
