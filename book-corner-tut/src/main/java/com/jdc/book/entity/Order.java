package com.jdc.book.entity;

import java.io.Serializable;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Bill bill;
	private Item item;
	private int count;
	
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getTotal() {
		return count * item.getPrice();
	}

}
