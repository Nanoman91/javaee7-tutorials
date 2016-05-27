package com.solt.jpa.model;

import javax.persistence.EntityManager;

import com.solt.jpa.entity.Division;

public class DivisionModel extends BaseAbstractModel<Division> {

	public DivisionModel(EntityManager em) {
		super(Division.class, em);
	}

}
