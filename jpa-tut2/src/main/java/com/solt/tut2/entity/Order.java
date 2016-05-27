package com.solt.tut2.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import static javax.persistence.GenerationType.TABLE;

@Entity
@Table(name="order_table")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = TABLE, generator = "OrderGenerator")
	@TableGenerator(name = "OrderGenerator", allocationSize = 1, initialValue = 0)
	private long id;
	
	@ManyToOne
	private Item item;
	@ManyToOne
	private Bill bill;
	private int count;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getTotal() {
		if(null != item) {
			return item.getPrice() * count;
		}
		return 0;
	}
}
