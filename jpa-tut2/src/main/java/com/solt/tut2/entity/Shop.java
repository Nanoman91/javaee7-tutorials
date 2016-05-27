package com.solt.tut2.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.TableGenerator;
import static javax.persistence.GenerationType.TABLE;

@Entity
@Table(name="shop")
public class Shop implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public enum Status {Valid, UnValid}
	
	@Id
	@GeneratedValue(strategy = TABLE, generator = "ShopGenerator")
	@TableGenerator(name = "ShopGenerator", allocationSize = 1, initialValue = 0)
	private long id;
	private String name;
	
	@ManyToMany
	@JoinTable(name = "shop_owner", 
		joinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "id"))
	private Set<Account> owners;
	
	@Embedded
	@ElementCollection
	@CollectionTable(name="shop_address")
	private Set<Address> addresses;
	
	@ElementCollection
	@CollectionTable(name="shop_phones")
	private Set<String> phones;
	
	@ElementCollection
	@CollectionTable(name="shop_emails")
	private Set<String> emails;
	
	@Enumerated
	private Status status;
	
	public Shop() {
		owners = new HashSet<>();
		addresses = new HashSet<>();
		phones = new HashSet<>();
		emails = new HashSet<>();
		status = Status.Valid;
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
	public Set<Account> getOwners() {
		return owners;
	}
	public void setOwners(Set<Account> owners) {
		for(Account ac : owners) {
			addOwner(ac);
		}
	}
	public void addOwner(Account account) {
		owners.add(account);
		addresses.add(account.getAddress());
		phones.addAll(account.getPhones());
		emails.addAll(account.getEmails());
		
	}
	public Set<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
	public void addAddress(Address address) {
		this.addresses.add(address);
	}
	public Set<String> getPhones() {
		return phones;
	}
	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}
	
	public void addPhone(String phone) {
		this.phones.add(phone);
	}
	public Set<String> getEmails() {
		return emails;
	}
	public void setEmails(Set<String> emails) {
		this.emails = emails;
	}
	public void addEmail(String email) {
		this.emails.add(email);
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

}
