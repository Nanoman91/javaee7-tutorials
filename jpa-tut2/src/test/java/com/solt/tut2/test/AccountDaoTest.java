package com.solt.tut2.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.solt.tut2.dao.Dao;
import com.solt.tut2.dao.imp.AccountDao;
import com.solt.tut2.entity.Account;
import com.solt.tut2.entity.Account.Role;
import com.solt.tut2.entity.Account.Status;
import com.solt.tut2.entity.Address;

public class AccountDaoTest {
	
	static EntityManagerFactory EMF;
	EntityManager em;
	Dao<Account> dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EMF = Persistence.createEntityManagerFactory("jpa-tut2");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EMF.close();
	}

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
		dao = new AccountDao(em);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void test1() {
		// create
		dao.add(getAccount());
		
		Account account = dao.findById("minlwin");
		assertEquals(Role.User, account.getRole());
		assertEquals(Status.Valid, account.getStatus());
	}

	@Test
	public void test2() {
		// find by ID
		Account a = dao.findById("minlwin");
		assertEquals(Role.User, a.getRole());
		assertEquals(Status.Valid, a.getStatus());
		assertEquals("Min Lwin", a.getName());
		assertEquals("12/YKN(N)006760", a.getNrc());
		assertEquals("0987677888", new ArrayList<>(a.getPhones()).get(0));
		assertEquals("minlwin@gmail.com", new ArrayList<>(a.getEmails()).get(0));
		
	}

	@Test
	public void test3() {
		// find
		assertEquals(1, dao.findAll().size());
		
		String where = "t.status = :status and :phone MEMBER OF t.phones";
		
		Map<String, Object> params = new HashMap<>();
		params.put("status", Status.Valid);
		params.put("phone", "0987677888");
		
		assertEquals(1, dao.find(where, params).size());
	}

	@Test
	public void test4() {
		// update
		Account a = dao.findById("minlwin");
		a.setStatus(Status.UnValid);
		
		dao.update(a);
		
		String where = "t.status = :status and :phone MEMBER OF t.phones";
		
		Map<String, Object> params = new HashMap<>();
		params.put("status", Status.Valid);
		params.put("phone", "0987677888");
		
		assertEquals(0, dao.find(where, params).size());
	}

	@Test
	public void test5() {
		// delete
		Account a = dao.findById("minlwin");
		dao.delete(a);
		
		assertEquals(0, dao.findAll().size());
	}
	
	public static Account getAccount() {
		Account account = new Account();
		account.setLoginId("minlwin");
		
		account.setAddress(getAddress());
		
		account.setName("Min Lwin");
		account.setNrc("12/YKN(N)006760");
		
		account.addPhone("0987677888");
		account.addEmail("minlwin@gmail.com");
		return account;
	}
	
	public static Address getAddress() {
		Address address = new Address();
		address.setAddress("No 52B/5F Insein Road");
		address.setStreet("Between Sanyeik Nyein 3 and 4 Street");
		address.setPostalCode("1001-404");
		address.setDivision("Yangon");
		address.setTownship("Kamayut");
		return address;
	}
	
	public static Account getBuyer() {
		Account buyer = new Account();
		Address address = new Address();
		address.setAddress("No 32/A");
		address.setStreet("Pyay Road");
		address.setTownship("Mayangone");
		address.setDivision("Yangon");
		address.setPostalCode("1000-001");
		
		buyer.setAddress(address);
		buyer.setName("Thidar");
		buyer.setLoginId("thidar");
		buyer.setNrc("12/MYG(N) 0092793");
		buyer.addEmail("thidar@gmail.com");
		buyer.addPhone("097667888");
		return buyer;
	}
}
