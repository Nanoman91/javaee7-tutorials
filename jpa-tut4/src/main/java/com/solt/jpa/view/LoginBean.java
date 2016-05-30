package com.solt.jpa.view;

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

    private String name;
    private String loginId;
    private String password;

    @Inject
    private UserModel userModel;
    
    @ErrorHandler
    public String login() {
		internalLogin(loginId, password);
    	return "/home?faces-redirect=true";
    }

    @ErrorHandler
    public String signUp() {
    	User user = new User();
    	user.setName(name);
    	user.setLoginId(loginId);
    	user.setPassword(password);
    	user.getSecurity().setCreateUser(user.getLoginId());
    	user.getSecurity().setModUser(user.getLoginId());
    	userModel.createUser(user);
    	internalLogin(loginId, password);
    	
    	return "/home?faces-redirect=true";
    }

    private void internalLogin(String loginId, String password) {
    	try {
        	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			request.login(loginId, password);
		} catch (ServletException e) {
			throw new ApplicationException(e);
		}
    }

	public String logout() {
    	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    	return "/home?faces-redirect=true";
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}