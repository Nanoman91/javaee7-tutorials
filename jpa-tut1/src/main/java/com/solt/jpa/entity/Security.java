package com.solt.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Security implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creation;
	@Temporal(TemporalType.TIMESTAMP)
	private Date modification;
	private int userId;
	
	public Security() {
		creation = new Date();
		modification = new Date();
	}
	
	public Date getCreation() {
		return creation;
	}
	public void setCreation(Date creation) {
		this.creation = creation;
	}
	public Date getModification() {
		return modification;
	}
	public void setModification(Date modification) {
		this.modification = modification;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creation == null) ? 0 : creation.hashCode());
		result = prime * result + ((modification == null) ? 0 : modification.hashCode());
		result = prime * result + userId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Security other = (Security) obj;
		if (creation == null) {
			if (other.creation != null)
				return false;
		} else if (!creation.equals(other.creation))
			return false;
		if (modification == null) {
			if (other.modification != null)
				return false;
		} else if (!modification.equals(other.modification))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	
}
