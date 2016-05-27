package com.solt.tut2.dao.imp;

import javax.persistence.EntityManager;

import com.solt.tut2.dao.AbstractDao;
import com.solt.tut2.entity.Address;

public class AddressDao extends AbstractDao<Address> {

	public AddressDao(EntityManager em) {
		super(Address.class, em);
	}

}
