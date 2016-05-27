package com.solt.cdi;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class CounterView {
	
	@CounterType(CounterTypes.Request)
	@Inject
	private Counter requestCounter;
	
	@CounterType(CounterTypes.Session)
	@Inject
	private Counter sessionCounter;
	
	@CounterType(CounterTypes.Application)
	@Inject
	private Counter applicationCounter;
	
	public String countUp() {
		requestCounter.countUp();
		sessionCounter.countUp();
		applicationCounter.countUp();
		return "";
	}
	
	public int getRequestCount() {
		return requestCounter.getCount();
	}
	
	public int getSessionCount() {
		return sessionCounter.getCount();
	}
	
	public int getApplicationCount() {
		return applicationCounter.getCount();
	}

}
