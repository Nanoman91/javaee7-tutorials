package com.jdc.book.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.jdc.book.entity.Bill;
import com.jdc.book.entity.Order;
import com.jdc.book.entity.User;

@ApplicationScoped
public class OrderModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Order> orders;

	@PostConstruct
	private void init() {
		orders = Collections.synchronizedList(new ArrayList<>());
	}

	public List<Bill> find(User user) {
		
		return orders.stream()
				.map(a -> a.getBill())
				.distinct()
				.filter(b -> b.getUser().equals(user))
				.collect(Collectors.toList());

	}
	
	public void add(List<Order> list) {
		this.orders.addAll(list);
	}

}
