package com.solt.jpa.view;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.solt.jpa.entity.User;
import com.solt.jpa.model.UserModel;
import com.solt.jpa.view.common.ErrorHandler;
import com.solt.jpa.view.common.LoginUser;

@Named
@ViewScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@LoginUser
	@Inject
    private User user;
	
	private String oldPass;
	private String newPass;
	private String confPass;
    
    @Inject
    private UserModel model;

    @ErrorHandler
    public void editUserInfo() {
    	user.getSecurity().setModification(new Date());
    	user.getSecurity().setModUser(user.getLoginId());
    	model.editUser(user);
    }

    @ErrorHandler
    public void changePass() {
    	user.getSecurity().setModification(new Date());
    	user.getSecurity().setModUser(user.getLoginId());
    	model.changePass(user.getLoginId(), oldPass, newPass, confPass);
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getConfPass() {
		return confPass;
	}

	public void setConfPass(String confPass) {
		this.confPass = confPass;
	}

}