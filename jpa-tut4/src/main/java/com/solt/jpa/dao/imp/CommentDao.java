package com.solt.jpa.dao.imp;

import com.solt.jpa.dao.AbstractDao;
import com.solt.jpa.entity.Comment;

public class CommentDao extends AbstractDao<Comment> {

	private static final long serialVersionUID = 1L;

	public CommentDao() {
    	super(Comment.class);
    }
}