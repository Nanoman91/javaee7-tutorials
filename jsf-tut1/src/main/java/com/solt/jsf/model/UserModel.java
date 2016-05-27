package com.solt.jsf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private List<User> list = new ArrayList<>();
	
	public void add(User u) {
		int size = list.size();
		if(size > 0) {
			int id  = list.get(size  - 1).getId() + 1;
			u.setId(id);
		} else {
			u.setId(list.size() + 1);
		}
		list.add(u);
	}
	
	public List<User> getAllUser() {
		return list;
	}
	
	public User findById(int id) {
		for (User user : list) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public void editUser(User u) {
		int num = -1;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == u.getId()) {
				num = i;
			}
		}
		
		if(num >= 0) {
			list.remove(num);
			list.add(num, u);
		}
	}

	public void delete(String id) {
		User u = findById(Integer.parseInt(id));
		list.remove(u);
	}

}
