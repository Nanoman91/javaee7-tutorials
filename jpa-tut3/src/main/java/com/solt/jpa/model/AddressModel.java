package com.solt.jpa.model;

import javax.persistence.EntityManager;

import com.solt.jpa.entity.Address;

public class AddressModel extends BaseAbstractModel<Address> {

	public AddressModel(EntityManager em) {
		super(Address.class, em);
	}

}
