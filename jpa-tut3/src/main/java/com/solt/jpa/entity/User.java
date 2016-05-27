package com.solt.jpa.entity;

import com.solt.jpa.entity.Address;
import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;
import static javax.persistence.CascadeType.ALL;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

public class User implements Serializable {

	   
	@Id
	private String loginId;
	private String name;
	private String phone;
	@Temporal(TemporalType.DATE)
	private Date dob;
	private String email;
	@OneToOne(cascade = ALL)
	private Address address;
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	public String getLoginId() {
		return this.loginId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}   
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}   
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
   
}
