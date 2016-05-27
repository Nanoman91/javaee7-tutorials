package com.solt.jsf.model.imp;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.solt.jsf.entity.Bill;
import com.solt.jsf.entity.Order;
import com.solt.jsf.entity.User;
import com.solt.jsf.model.OrderService;

@Local
@Stateless
public class OrderServiceImp implements OrderService {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void addAll(List<Order> orders) {
		for (Order order : orders) {
			em.persist(order);
		}
	}

	@Override
	public void addOrder(Order order) {
		em.persist(order);
	}

	@Override
	public List<Bill> getUserBill(User u) {
		String sql = "select b from Bill b where b.user = :user";
		TypedQuery<Bill> q = em.createQuery(sql, Bill.class);
		q.setParameter("user", u);
		return q.getResultList();
	}

}
