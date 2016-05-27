package com.solt.tut2.dao.imp;

import javax.persistence.EntityManager;

import com.solt.tut2.dao.AbstractDao;
import com.solt.tut2.entity.BillPK;

public class BillPKDao extends AbstractDao<BillPK> {

	public BillPKDao(EntityManager em) {
		super(BillPK.class, em);
	}

}
