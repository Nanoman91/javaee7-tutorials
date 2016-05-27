package com.solt.jsf.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

import com.solt.jsf.model.User;
import com.solt.jsf.model.User.Education;
import com.solt.jsf.model.User.Gender;
import com.solt.jsf.model.UserModel;

@Model
public class UserUploadBean {
	
	private Part file;
	
	@Inject
	private UserModel model;

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
	
	public String upload() {
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line = null;
			
			while(null != (line = br.readLine())) {
				User u = getUser(line);
				model.add(u);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage("File layout error!", "Please check your file layout!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return "/userList?faces-redirect=true";
	}

	private User getUser(String line) throws Exception {
		String [] array = line.split("\t");
		User u = new User();
		// name
		u.setName(array[0]);
		// dob
		u.setDob(new SimpleDateFormat("yyyy-MM-dd").parse(array[1]));
		// gender
		u.setGender(Gender.valueOf(array[2]));
		// education
		u.setEducation(Education.valueOf(array[3]));
		// phone
		u.setPhone(array[4]);
		// email
		u.setEmail(array[5]);
		// address
		u.setAddress(array[6]);
		
		// interest
		String strInterest = array[7];
		if(null != strInterest && !strInterest.isEmpty()) {
			u.setInterest(Arrays.stream(strInterest.split(","))
					.map(a -> a.trim())
					.sorted()
					.collect(Collectors.toSet()));	
		}
		return u;
	}

}
