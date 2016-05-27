package com.solt.jpa.model;

import javax.persistence.EntityManager;

import com.solt.jpa.entity.Item;

public class ItemModel {
	
	private EntityManager em;
	
	public ItemModel(EntityManager em) {
		this.em = em;
	}

	public void insert(Item item) {
		
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
		
	}
	
	public Item findById(int id) {
		return em.find(Item.class, id);
	}
	
	public void delete(Item item) {
		
	}
	
	public void update(Item item) {
		
	}
}
