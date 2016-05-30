package com.solt.jpa.view;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.solt.jpa.entity.User;
import com.solt.jpa.model.UserModel;
import com.solt.jpa.view.common.ApplicationException;
import com.solt.jpa.view.common.ErrorHandler;

@Model
public class LoginBean {

    private User user;

    @Inject
    private UserModel userModel;
    
    @PostConstruct
    private void init() {
    	user = new User();
    }

    @ErrorHandler
    public void login() {
		internalLogin(user.getLoginId(), user.getPassword());
    }

    @ErrorHandler
    public void signUp() {
    	user.getSecurity().setCreateUser(user.getLoginId());
    	user.getSecurity().setModUser(user.getLoginId());
    	userModel.createUser(user);
    	internalLogin(user.getLoginId(), user.getPassword());
    }

    private void internalLogin(String loginId, String password) {
    	try {
        	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			request.login(user.getLoginId(), user.getPassword());
		} catch (ServletException e) {
			throw new ApplicationException(e);
		}
    }

	public String logout() {
    	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    	return "/home?faces-redirect=true";
    }

}