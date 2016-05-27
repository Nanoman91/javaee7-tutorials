package com.solt.tut2.dao.imp;

import javax.persistence.EntityManager;

import com.solt.tut2.dao.AbstractDao;
import com.solt.tut2.entity.Shop;

public class ShopDao extends AbstractDao<Shop> {

	public ShopDao(EntityManager em) {
		super(Shop.class, em);
	}

}
