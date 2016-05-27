package com.solt.jsf.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public enum Gender {Male, Female}
	public enum Education {BEHS, College, Master, Phd}
	
	private int id;
	private String name;
	private Date dob;
	private Gender gender;
	private Education education;
	private String phone;
	private String email;
	private String address;
	private Set<String> interest = new LinkedHashSet<>();

	public List<String> getInterestList() {
		return new ArrayList<>(interest);
	}
	
	public Set<String> getInterest() {
		return interest;
	}

	public void setInterest(Set<String> interest) {
		this.interest = interest;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}
	
}
