package com.solt.jsf.model.imp;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.solt.jsf.entity.User;
import com.solt.jsf.model.UserService;

@Local
@Stateless
public class UserServiceImp implements UserService {
	
	@PersistenceContext
	private EntityManager em;

	@Inject
	private Event<User> signInEvent;

	@Override
	public void add(User user) {
		em.persist(user);
	}

	@Override
	public void signIn(User user) {
		User u = findByName(user.getName());
		if(null == u) {
			u = user;
			em.persist(u);
		} else {
			if(user.getAddress() != null && !user.getAddress().isEmpty())
				u.setAddress(user.getAddress());
			if(user.getEmail() != null && !user.getEmail().isEmpty())
				u.setEmail(user.getEmail());
			if(user.getPhone() != null && !user.getPhone().isEmpty())
				u.setPhone(user.getPhone());
		}
		
		signInEvent.fire(u);
	}
	
	private User findByName(String name) {
		String sql = "select u from User u where u.name = :name";
		TypedQuery<User> q = em.createQuery(sql, User.class);
		q.setParameter("name", name);
		List<User> list = q.getResultList();
		for (User user : list) {
			return user;
		}
		return null;
	}

}
