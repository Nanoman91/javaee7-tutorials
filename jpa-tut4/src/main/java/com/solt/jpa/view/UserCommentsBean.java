package com.solt.jpa.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.solt.jpa.entity.Comment;
import com.solt.jpa.entity.User;
import com.solt.jpa.model.BlogModel;
import com.solt.jpa.view.common.ErrorHandler;
import com.solt.jpa.view.common.LoginUser;

@Named
@ViewScoped
public class UserCommentsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Comment> comments;

    @Inject
    private BlogModel model;
    @Inject
    @LoginUser
    private User loginUser;
    
    private Comment selectedComment;
    
    @PostConstruct
    private void init() {
    	comments = model.getUserComments(loginUser);
    }

    @ErrorHandler
    public void edit(Comment comment) {
    	this.selectedComment = comment;
    }
    
    @ErrorHandler
    public void save(Comment comment) {
    	comment.getSecurity().setModification(new Date());
    	comment.getSecurity().setModUser(loginUser.getLoginId());
    	model.saveComment(comment);
    	
    	init();
    }

    @ErrorHandler
    public void delete(Comment comment) {
    	comment.getBlog().getComments().remove(comment);
    	model.saveBlog(comment.getBlog());
    }
    
    public List<Comment> getComments() {
		return comments;
	}
    
    public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment getSelectedComment() {
		return selectedComment;
	}

	public void setSelectedComment(Comment selectedComment) {
		this.selectedComment = selectedComment;
	}
 
}