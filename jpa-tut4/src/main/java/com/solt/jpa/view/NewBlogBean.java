package com.solt.jpa.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    
    private boolean publish;
    private String tags;
    
    @PostConstruct
    private void init() {
    	blog = new Blog();
    	blog.setUser(loginUser);
    }

    @ErrorHandler
    public String save() {
    	if(null != tags) {
    		Set<String> set = new HashSet<>(Arrays.asList(tags.split(",")));
        	blog.setTags(set);
    	}
		model.createBlog(blog);
		return "/blog?faces-redirect=true&id=" + blog.getId();
    }

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public boolean isPublish() {
		return publish;
	}

	public void setPublish(boolean publish) {
		this.publish = publish;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}