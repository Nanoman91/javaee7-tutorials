package com.solt.jsf.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;

import com.solt.jsf.entity.Bill;
import com.solt.jsf.entity.Item;
import com.solt.jsf.entity.Order;
import com.solt.jsf.entity.User;

@Named
@SessionScoped
public class CartBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private User user;
	private List<Order> orders;
	
	@PostConstruct
	private void init() {
		user = new User();
		orders = new ArrayList<>();
	}
	
	public boolean isLogin() {
		return (null != user.getName() && !user.getName().isEmpty());
	}
	
	public String getCount() {
		if(null != orders && orders.size() > 0) {
			return String.valueOf(orders.stream().mapToInt(od -> od.getCount()).sum());
		}
		return "";
	}
	
	public void countUp(Order order) {
		order.setCount(order.getCount() + 1);
	}
	
	public void countDown(Order order) {
		order.setCount(order.getCount() - 1);
		
		if(order.getCount() == 0) {
			orders.remove(order);
		}
	}

	public void addToCard(Item item) {
		boolean oldItem = false;
		
		for (Order od : orders) {
			if(od.getItem().getItem().equals(item.getItem())) {
				od.setCount(od.getCount() + 1);
				oldItem = true;
			} 
		}
		
		if(!oldItem) {
			Order order = new Order();
			order.setCount(1);
			order.setItem(item);
			orders.add(order);
		}
		
	}
	
	public void remove(Order item) {
		orders.remove(item);
	}

	public String clear() {
		orders.clear();
		return "/index?faces-redirect=true";
	}
	
	public void initCart() {
		orders = new ArrayList<>();
	}
	
	public Bill getBill() {
		
		Bill bill = new Bill();
		bill.setOrders(orders);
		bill.setUser(user);
		return bill;
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
		this.orders = orders;
	}
	
	public void observeLogin(@Observes User user) {
		this.user = user;
	}
	
}
