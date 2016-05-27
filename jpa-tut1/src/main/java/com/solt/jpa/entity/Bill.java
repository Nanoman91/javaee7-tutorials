package com.solt.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="bill")
@IdClass(BillPK.class)
public class Bill implements Serializable{

	private static final long serialVersionUID = 1L;
	public enum Status {Valid, Unvalid}

	@Id
	private int userId;
	
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Enumerated
	private Status status;
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
