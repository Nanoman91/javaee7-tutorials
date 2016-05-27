package com.solt.jpa.dao.imp;

import com.solt.jpa.dao.AbstractDao;
import com.solt.jpa.entity.Blog;

public class BlogDao extends AbstractDao<Blog> {

	private static final long serialVersionUID = 1L;

	public BlogDao() {
		super(Blog.class);
	}

}