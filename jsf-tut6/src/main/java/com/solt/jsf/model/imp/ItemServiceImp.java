package com.solt.jsf.model.imp;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.solt.jsf.entity.Item;
import com.solt.jsf.model.ItemService;

@Local
@Stateless
public class ItemServiceImp implements ItemService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Item> getAllItems() {
		String sql = "select i from Item i";
		return em.createQuery(sql, Item.class).getResultList();
	}

}
