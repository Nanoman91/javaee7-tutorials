package com.solt.jsf.model;

import java.util.List;

import javax.ejb.Local;

import com.solt.jsf.entity.Bill;
import com.solt.jsf.entity.Order;
import com.solt.jsf.entity.User;

@Local
public interface OrderService {

	void addAll(List<Order> orders);

	void addOrder(Order order);

	List<Bill> getUserBill(User u);

}