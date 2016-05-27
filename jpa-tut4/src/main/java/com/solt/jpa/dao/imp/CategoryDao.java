package com.solt.jpa.dao.imp;

import com.solt.jpa.dao.AbstractDao;
import com.solt.jpa.entity.Category;

public class CategoryDao extends AbstractDao<Category> {

	private static final long serialVersionUID = 1L;

	public CategoryDao() {
    	super(Category.class);
    }

}