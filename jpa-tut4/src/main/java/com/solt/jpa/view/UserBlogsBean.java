package com.solt.jpa.view;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.solt.jpa.entity.Blog;
import com.solt.jpa.entity.User;
import com.solt.jpa.entity.Blog.Status;
import com.solt.jpa.model.BlogModel;
import com.solt.jpa.model.BlogModel.SearchParam;
import com.solt.jpa.view.common.ErrorHandler;
import com.solt.jpa.view.common.LoginUser;

@Named
@ViewScoped
public class UserBlogsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Blog> blogs;

	@Inject
    private BlogModel model;
	@Inject
	@LoginUser
	private User loginUser;
	
	@ErrorHandler
	@PostConstruct
	private void init() {
		Map<SearchParam, Object> params = new HashMap<>();
		params.put(SearchParam.User, loginUser);
		blogs = model.searchBlog(params);
	}

    @ErrorHandler
    public void delete(Blog blog) {
    	model.deleteBlog(blog);
    	init();
    }
    
    @ErrorHandler
    public void publish(Blog b) {
    	b.setStatus(Status.Published);
    	b.getSecurity().setModification(new Date());
    	b.getSecurity().setModUser(loginUser.getLoginId());
    	model.saveBlog(b);
    }
    
    @ErrorHandler
    public void unPublish(Blog b) {
    	b.setStatus(Status.Edit);
    	b.getSecurity().setModification(new Date());
    	b.getSecurity().setModUser(loginUser.getLoginId());
    	model.saveBlog(b);
    }

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

}