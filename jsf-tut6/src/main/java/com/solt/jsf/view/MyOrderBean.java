package com.solt.jsf.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.solt.jsf.entity.Bill;
import com.solt.jsf.model.OrderService;

@Model
public class MyOrderBean {

	private List<Bill> myBill;
	
	@Inject
	private OrderService model;
	@Inject
	private CartBean cart;
	
	@PostConstruct
	private void init() {
		myBill = model.getUserBill(cart.getUser());
	}

	public List<Bill> getMyBill() {
		return myBill;
	}

	public void setMyBill(List<Bill> myBill) {
		this.myBill = myBill;
	}
	
	
}
