package com.solt.jpa.model;

import javax.persistence.EntityManager;

import com.solt.jpa.entity.Township;

public class TownshipModel extends BaseAbstractModel<Township> {

	public TownshipModel(EntityManager em) {
		super(Township.class, em);
	}

}
