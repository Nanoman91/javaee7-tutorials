package com.solt.tut2.dao.imp;

import javax.persistence.EntityManager;

import com.solt.tut2.dao.AbstractDao;
import com.solt.tut2.entity.Order;

public class OrderDao extends AbstractDao<Order> {

	public OrderDao(EntityManager em) {
		super(Order.class, em);
	}

}
