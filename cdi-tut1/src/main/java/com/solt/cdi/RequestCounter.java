package com.solt.cdi;

import javax.enterprise.context.RequestScoped;

@CounterType(CounterTypes.Request)
@RequestScoped
public class RequestCounter implements Counter{
	
	private int count;

	@Override
	public void countUp() {
		count ++;
	}

	@Override
	public int getCount() {
		return count;
	}

}
