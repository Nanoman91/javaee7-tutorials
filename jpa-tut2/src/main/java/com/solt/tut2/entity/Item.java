package com.solt.tut2.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import static javax.persistence.GenerationType.TABLE;
import javax.persistence.ManyToOne;

@Entity
@Table(name="item")
public class Item implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public enum Status {SoldOut, InStock}

	@Id
	@GeneratedValue(strategy = TABLE, generator = "ItemGenerator")
	@TableGenerator(name = "ItemGenerator", allocationSize = 1, initialValue = 0)
	private long id;
	private String name;
	private int price;
	private String photo;
	@Enumerated
	private Status status;
	
	@ManyToOne
	private Shop shop;
	
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
