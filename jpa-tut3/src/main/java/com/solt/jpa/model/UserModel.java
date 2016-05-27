package com.solt.jpa.model;

import javax.persistence.EntityManager;

import com.solt.jpa.entity.User;

public class UserModel extends BaseAbstractModel<User> {

	public UserModel(EntityManager em) {
		super(User.class, em);
	}

}
