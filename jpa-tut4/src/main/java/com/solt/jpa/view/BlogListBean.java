package com.solt.jpa.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.solt.jpa.entity.Blog;
import com.solt.jpa.entity.Blog.Status;
import com.solt.jpa.entity.Category;
import com.solt.jpa.entity.User;
import com.solt.jpa.model.BlogModel;
import com.solt.jpa.model.BlogModel.SearchParam;
import com.solt.jpa.model.CategoryModel;
import com.solt.jpa.model.UserModel;
import com.solt.jpa.view.common.ErrorHandler;
import com.solt.jpa.view.common.ParamsUtils;

@Named
@ViewScoped
public class BlogListBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Blog> blogs;
    private Category category;
    private String tag;
    private String keyword;
    private User user;
    
    @Inject
    private BlogModel model;
    @Inject
    private UserModel userModel;
    @Inject
    private CategoryModel catModel;
    
    @PostConstruct
    private void init() {
    	try {
        	
        	String catId = ParamsUtils.getRequestParam("cat");
        	if(null != catId) {
        		category = catModel.findById(Integer.parseInt(catId));
        	}
        	
        	String loginId = ParamsUtils.getRequestParam("user");
        	
        	if(null != loginId) {
        		user = userModel.getUser(loginId);
        	}
        	
        	search();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    @ErrorHandler
    public void delete(Blog blog) {
    	model.deleteBlog(blog);
    }

    @ErrorHandler
    public void search() {
    	Map<SearchParam, Object> params = new HashMap<>();
    	params.put(SearchParam.Category, category);
    	params.put(SearchParam.Tag, tag);
    	params.put(SearchParam.Keyword, keyword);
    	params.put(SearchParam.User, user);
    	params.put(SearchParam.Status, Status.Published);
    	
    	blogs = model.searchBlog(params);
    }
    
    public String searchByKeyword() {
    	search();
    	return "/home";
    }
    
	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}