package com.solt.cdi;

import javax.enterprise.context.ApplicationScoped;

@CounterType(CounterTypes.Application)
@ApplicationScoped
public class ApplicationCounter implements Counter {
	
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
