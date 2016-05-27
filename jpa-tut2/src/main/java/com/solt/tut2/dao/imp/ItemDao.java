package com.solt.tut2.dao.imp;

import javax.persistence.EntityManager;

import com.solt.tut2.dao.AbstractDao;
import com.solt.tut2.entity.Item;

public class ItemDao extends AbstractDao<Item> {

	public ItemDao(EntityManager em) {
		super(Item.class, em);
	}

}
