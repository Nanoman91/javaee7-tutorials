package com.jdc.book.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.jdc.book.entity.Bill;
import com.jdc.book.entity.Item;
import com.jdc.book.entity.Order;
import com.jdc.book.model.OrderModel;
import com.jdc.book.utils.MessageUtils;
import com.jdc.book.utils.SecurityInfo;

@Named
@SessionScoped
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Order> orders;

	@Inject
	private SecurityInfo security;
	@Inject
	private OrderModel model;
	
	@PostConstruct
	private void init() {
		orders = new ArrayList<>();
	}
	
	
	public void addToCart(Item item) {
		
		Order order = orders.stream().filter(od -> od.getItem()
				.equals(item)).findFirst().orElse(null);
		
		if(null == order) {
			Order od = new Order();
			od.setCount(1);
			od.setItem(item);
			orders.add(od);
		} else {
			order.setCount(order.getCount() + 1);
		}
	}
	
	public String showCartView() {
		if(!security.isLogin()) {
			MessageUtils.addMessage("You need to sign up or sign in for check out operation!");
			return "/index";
		}
		
		if(security.isNoContact()) {
			MessageUtils.addMessage("You need to enter contact imformations for check out operation!");
			return "/profile";
		}
		
		return "/shopping";
	}
	
	public void countUp(Order od) {
		od.setCount(od.getCount() + 1);
	}
	
	public void countDown(Order od) {
		od.setCount(od.getCount() - 1);
		if(od.getCount() == 0) {
			orders.remove(od);
		}
	}
	
	public void delete(Order od) {
		this.orders.remove(od);
	}
	
	public void clear() {
		orders = new ArrayList<>();
	}
	
	public String takeOrder() {
		model.add(getBill().getOrders());
		clear();
		return "/orders?faces-redirect=true";
	}
	
	public Bill getBill() {
		Bill b = new Bill();
		b.setUser(security.getUser());
		b.setBusinessDate(new Date());
		b.setOrders(orders);
		return b;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
