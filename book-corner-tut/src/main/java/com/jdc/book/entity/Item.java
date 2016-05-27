package com.jdc.book.entity;

import java.io.Serializable;

public class Item implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int price;
	
	public Item(String name, int price) {
		super();
		this.name = name;
		this.price = price;
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
	
	public String getPicture() {
		return name.replaceAll(" ", "").concat(".png");
	}

}
