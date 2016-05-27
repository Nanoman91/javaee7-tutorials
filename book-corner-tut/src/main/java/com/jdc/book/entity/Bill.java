package com.jdc.book.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bill implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private User user;
	private List<Order> orders;
	private Date businessDate;
	
	public Bill() {
		orders = new ArrayList<>();
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		if(null != orders) {
			this.orders.addAll(orders);
			this.orders.forEach(o -> o.setBill(this));
		}
	}
	public Date getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
	}
	
	public int getTotal() {
		return this.orders.stream().mapToInt(o -> o.getTotal()).sum();
	}
	
	public int getCount() {
		return this.orders.stream().mapToInt(o -> o.getCount()).sum();
	}

}
