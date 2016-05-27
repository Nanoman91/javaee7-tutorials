package com.solt.jpa.model.imp;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.solt.jpa.dao.imp.UserDao;
import com.solt.jpa.entity.User;
import com.solt.jpa.model.UserModel;
import com.solt.jpa.view.common.ApplicationException;
import com.solt.jpa.view.common.ApplicationException.ErrorType;
import com.solt.jpa.view.common.PasswordUtils;

@Local
@Stateless
public class UserModelImp implements UserModel {

	private static final long serialVersionUID = 1L;

	@Inject
    public UserDao userDao;

    public void checkLoginId(String loginId) {
    	User user = userDao.findById(loginId);
    	if(null != user) {
    		throw new ApplicationException("Your login Id has been used. Please enter another login id.", ErrorType.Warning);
    	}
    }

    public void createUser(User user) {
    	user.setPassword(PasswordUtils.encript(user.getPassword()));
    	userDao.insert(user);
    }

    public void editUser(User user) {
    	userDao.update(user);
    }

	@Override
	public User getUser(String loginId) {
		return userDao.findById(loginId);
	}

	@Override
	public void changePass(String loginId, String oldPass, String newPass, String confPass) {
		
		// check user old password
		User user = userDao.findById(loginId);
		if(!user.getPassword().equals(PasswordUtils.encript(oldPass))) {
			throw new ApplicationException("Please check your old password!", ErrorType.Error);
		}
		
		// check new password
		if(!newPass.equals(confPass)) {
			throw new ApplicationException("Please check your new password and confirm password!", ErrorType.Error);
		}
		
		// set new password
		user.setPassword(PasswordUtils.encript(newPass));
		
		// update user
		userDao.update(user);
	}

}