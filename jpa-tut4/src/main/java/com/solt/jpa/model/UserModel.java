package com.solt.jpa.model;

import java.io.Serializable;

import javax.ejb.Local;

import com.solt.jpa.entity.User;

@Local
public interface UserModel extends Serializable {
	
	public User getUser(String loginId);

    public void checkLoginId(String loginId);

    public void createUser(User user);

    public void editUser(User user);

    public void changePass(String loginId, String oldPass, String newPass, String confPass);

}