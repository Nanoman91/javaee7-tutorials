package com.solt.jpa.entity;

import com.solt.jpa.entity.Township;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.CascadeType.DETACH;

/**
 * Entity implementation class for Entity: Address
 *
 */
@Entity
public class Address implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String address;
	
	@ManyToOne(cascade = { PERSIST, MERGE, REFRESH, DETACH })
	private Township township;
	
	@OneToOne(mappedBy = "address")
	private User user;
	private static final long serialVersionUID = 1L;

	public Address() {
		super();
	} 
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}   
	public Township getTownship() {
		return this.township;
	}

	public void setTownship(Township township) {
		this.township = township;
	}
   
}
