package com.solt.jpa.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable {
	
	public enum AddressType {Residense, Office, Delivery};
	   
	@Id
	private String login;
	private String name;
	private String password;
	
	@Embedded
	private Security security;
	
	@Enumerated(EnumType.STRING)
	@Embedded
	@ElementCollection
	@CollectionTable
	@MapKeyColumn(name="type")
	private Map<AddressType, Address> addresses;
	
	private static final long serialVersionUID = 1L;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public Map<AddressType, Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Map<AddressType, Address> addresses) {
		this.addresses = addresses;
	}

	
}
