package com.solt.tut2.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public enum Status {Valid, UnValid}
	public enum Role {Admin, User}
	
	@Id
	@Column(name="login_id")
	private String loginId;
	private String name;
	private String nrc;
	@Embedded
	private Address address;
	@CollectionTable(name = "account_phones", 
			joinColumns = @JoinColumn(name = "login_id", referencedColumnName = "login_id"))
	@ElementCollection
	private Set<String> phones;
	@CollectionTable(name = "account_emails", 
			joinColumns = @JoinColumn(name = "login_id", referencedColumnName = "login_id"))
	@ElementCollection
	private Set<String> emails;
	@Enumerated
	private Status status;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public Account() {
		status = Status.Valid;
		role = Role.User;
		phones = new HashSet<>();
		emails = new HashSet<>();
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNrc() {
		return nrc;
	}
	public void setNrc(String nrc) {
		this.nrc = nrc;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Set<String> getPhones() {
		return phones;
	}
	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}
	public void addPhone(String phone) {
		this.phones.add(phone);
	}
	public Set<String> getEmails() {
		return emails;
	}
	public void setEmails(Set<String> emails) {
		this.emails = emails;
	}
	public void addEmail(String email) {
		this.emails.add(email);
	}

}
