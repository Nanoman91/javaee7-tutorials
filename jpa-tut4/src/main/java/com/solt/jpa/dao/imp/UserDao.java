package com.solt.jpa.dao.imp;

import com.solt.jpa.dao.AbstractDao;
import com.solt.jpa.entity.User;

public class UserDao extends AbstractDao<User> {

	private static final long serialVersionUID = 1L;

	public UserDao() {
    	super(User.class);
    }

}