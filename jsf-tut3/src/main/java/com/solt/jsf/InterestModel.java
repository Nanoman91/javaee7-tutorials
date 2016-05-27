package com.solt.jsf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@MemoryModel
@ApplicationScoped
public class InterestModel implements InterestDataModel {

	private List<MyInterest> data;
	
	@PostConstruct
	private void init() {
		data = new ArrayList<>();
	}
	
	/* (non-Javadoc)
	 * @see com.solt.jsf.InterestDataModel#add(com.solt.jsf.MyInterest)
	 */
	@Override
	public void add(MyInterest interest) {
		this.data.add(interest);
	}
	
	/* (non-Javadoc)
	 * @see com.solt.jsf.InterestDataModel#getAllData()
	 */
	@Override
	@Named("allInterest")
	@Produces
	public List<MyInterest> getAllData() {
		return new ArrayList<>(data);
	}
	
	@Produces
	@Named
	public List<String> getSelectInterest() {
		return Arrays.asList("Internet", "Fashion", "Dance", "Shopping", "Men Wares");
	}
	
}
