package com.solt.tut2.dao.imp;

import javax.persistence.EntityManager;

import com.solt.tut2.dao.AbstractDao;
import com.solt.tut2.entity.Bill;

public class BillDao extends AbstractDao<Bill> {

	public BillDao(EntityManager em) {
		super(Bill.class, em);
	}

}
