package com.solt.jpa.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="item")
public class Item implements Serializable {

	public enum Status {ON, OFF}
	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="item_name")
	private String name;
	private int price;
	@Temporal(TemporalType.DATE)
	@Column(name="expire_date")
	private Date expireDate;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ElementCollection
	@CollectionTable(name="item_keyword")
	private Set<String> keywords;
	
	@Embedded
	private Security security;
	
	private static final long serialVersionUID = 1L;

	
	public Set<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(Set<String> keywords) {
		this.keywords = keywords;
	}
	public Security getSecurity() {
		return security;
	}
	public void setSecurity(Security security) {
		this.security = security;
	}
	public Item() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
   
}
