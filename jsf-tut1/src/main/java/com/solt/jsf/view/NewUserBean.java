package com.solt.jsf.view;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.solt.jsf.model.User;
import com.solt.jsf.model.User.Education;
import com.solt.jsf.model.User.Gender;
import com.solt.jsf.model.UserModel;

@Named
@RequestScoped
public class NewUserBean {
	
	@Inject
	private User user;
	@Inject
	private UserModel model;
	
	private Part file;
	private boolean agree;
	
	private List<Gender> genders = Arrays.asList(Gender.values());
	private List<Education> educations = Arrays.asList(Education.values());
	
	private String id;
	
	@PostConstruct
	private void init() {
		
		try {
			
			id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

			if(null != id && !id.isEmpty()) {
				user = model.findById(Integer.parseInt(id));
				agree = true;
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public boolean isAgree() {
		return agree;
	}

	public void setAgree(boolean agree) {
		this.agree = agree;
	}

	public User getUser() {
		return user;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
		
	}
	
	public String save() {

		if(agree) {
			if(null != id && !id.isEmpty()) {
				user.setId(Integer.parseInt(id));
				model.editUser(user);
			} else {
				model.add(user);
			}
			return "/userList";
		} else {
			FacesMessage message = new FacesMessage("Agreement Problem", "You need to agree to save information.");
			FacesContext.getCurrentInstance().addMessage("agreement", message);
		}
		return "";
	}
	
	public List<Gender> getGenders() {
		return genders;
	}

	public void setGenders(List<Gender> genders) {
		this.genders = genders;
	}

	public List<Education> getEducations() {
		return educations;
	}

	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}

}
