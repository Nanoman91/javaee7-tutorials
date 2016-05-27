package com.jdc.book.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.jdc.book.entity.Bill;
import com.jdc.book.entity.User;
import com.jdc.book.model.OrderModel;
import com.jdc.book.utils.LoginUser;

@Model
public class MyOrderBean {

	private List<Bill> bills;
	
	@Inject
	private OrderModel model;
	
	@LoginUser
	@Inject
	private User loginUser;

	@PostConstruct
	private void init() {
		bills = model.find(loginUser);
	}
	
	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	
	
}
