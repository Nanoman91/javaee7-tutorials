package com.solt.jpa.view;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.solt.jpa.entity.Blog;
import com.solt.jpa.entity.User;
import com.solt.jpa.model.BlogModel;
import com.solt.jpa.view.common.ErrorHandler;
import com.solt.jpa.view.common.LoginUser;

@Model
public class NewBlogBean {

    private Blog blog;

    @Inject
    @LoginUser
    private User loginUser;

    @Inject
    private BlogModel model;
    
    @PostConstruct
    private void init() {
    	blog = new Blog();
    	blog.setUser(loginUser);
    }

    @ErrorHandler
    public String save() {
		model.createBlog(blog);
		return "/blog?faces-redirect=true&id=" + blog.getId();
    }

}