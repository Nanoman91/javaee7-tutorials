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
import com.solt.jpa.entity.Blog.Status;
import com.solt.jpa.entity.Category;
import com.solt.jpa.model.BlogModel;
import com.solt.jpa.model.BlogModel.SearchParam;
import com.solt.jpa.view.common.ErrorHandler;

@Named
@ViewScoped
public class BlogListBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Blog> blogs;
    private Category category;
    private String tag;
    private String keyword;
    
    private Date dateFrom;
    private Date dateTo;
    
    @Inject
    private BlogModel model;
    
    @PostConstruct
    private void init() {
    	dateTo = new Date();
    	// TODO find start of month
    	search();
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
    	params.put(SearchParam.DateFrom, dateFrom);
    	params.put(SearchParam.DateTo, dateTo);
    	params.put(SearchParam.Status, Status.Published);
    	
    	blogs = model.searchBlog(params);
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

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
}