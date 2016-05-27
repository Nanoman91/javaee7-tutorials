package com.solt.tut2.dao.imp;

import javax.persistence.EntityManager;

import com.solt.tut2.dao.AbstractDao;
import com.solt.tut2.entity.Account;

public class AccountDao extends AbstractDao<Account> {

	public AccountDao(EntityManager em) {
		super(Account.class, em);
	}

}
