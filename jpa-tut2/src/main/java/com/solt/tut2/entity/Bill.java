package com.solt.tut2.entity;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bill")
public class Bill implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private BillPK id;
	
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "login_id", insertable = false, updatable = false)
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "shop_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Shop shop;
	
	@OneToMany(mappedBy = "bill", cascade = ALL, orphanRemoval = true)
	private List<Order> orders;
	
	public Bill() {
		orders = new ArrayList<>();
		id = new BillPK();
	}
	
	public BillPK getId() {
		return id;
	}
	public void setId(BillPK id) {
		this.id = id;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
		this.id.setLoginId(account.getLoginId());
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
		this.id.setShopId(shop.getId());
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		for(Order od : orders) {
			addOrder(od);
		}
	}
	
	public void addOrder(Order od) {
		od.setBill(this);
		orders.add(od);
	}
	
	public int getTotal() {
		return orders.stream().mapToInt(a -> a.getTotal()).sum();
	}

}
