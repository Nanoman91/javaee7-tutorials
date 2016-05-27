package com.solt.jpa.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Division
 *
 */
@Entity

public class Division implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@OneToMany(mappedBy = "division", orphanRemoval = true)
	private List<Township> townships;
	
	private static final long serialVersionUID = 1L;

	public Division() {
		super();
	}   
	
	public List<Township> getTownships() {
		return townships;
	}


	public void setTownships(List<Township> townships) {
		this.townships = townships;
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
   
}
