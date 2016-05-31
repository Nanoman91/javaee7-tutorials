package com.solt.jpa.view;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.solt.jpa.entity.Blog;
import com.solt.jpa.entity.Comment;
import com.solt.jpa.entity.User;
import com.solt.jpa.entity.Blog.Status;
import com.solt.jpa.model.BlogModel;
import com.solt.jpa.view.common.ErrorHandler;
import com.solt.jpa.view.common.LoginUser;

@Named
@ViewScoped
public class BlogBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Blog blog;
    private String newComment;
    private Comment selectedComment;
    
    @LoginUser
    @Inject
    private User loginUser;

    @Inject
    private BlogModel model;
    

    @PostConstruct
    public void init() {
    	String id = FacesContext.getCurrentInstance()
    				.getExternalContext()
    				.getRequestParameterMap().get("id");
    	if(null != id) {
    		blog = model.findBlogById(Long.parseLong(id));
    		
    	}
    	
    	if(null == loginUser) {
    		if(blog.getStatus().equals(Status.Edit)) {
    			blog = null;
    		}
    	}
    	
    	if(null != loginUser) {
    		if(!blog.getUser().getLoginId().equals(loginUser.getLoginId())) {
    			blog = null;
    		}
    	}
    }

    @ErrorHandler
    public void save() {
    	model.saveBlog(blog);
    }

    @ErrorHandler
    public void addComment() {
    	Comment comment = new Comment();
    	comment.setComment(newComment);
    	comment.setUser(loginUser);
    	blog.addComment(comment);
    	model.saveBlog(blog);
    }

    @ErrorHandler
    public void editComment(Comment comment) {
    	this.selectedComment = comment;
    }

    @ErrorHandler
    public void saveComment() {
    	selectedComment.getSecurity().setModification(new Date());
    	selectedComment.getSecurity().setModUser(loginUser.getLoginId());
    	model.saveComment(selectedComment);
    }

    @ErrorHandler
    public void deleteComment(Comment comment) {
    	blog.removeComment(comment);
    	model.saveBlog(blog);
    }

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public String getNewComment() {
		return newComment;
	}

	public void setNewComment(String newComment) {
		this.newComment = newComment;
	}

	public Comment getSelectedComment() {
		return selectedComment;
	}

	public void setSelectedComment(Comment selectedComment) {
		this.selectedComment = selectedComment;
	}
    
    

}