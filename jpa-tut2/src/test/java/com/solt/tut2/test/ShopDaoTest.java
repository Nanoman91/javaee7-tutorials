package com.solt.tut2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.List;
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
import com.solt.tut2.dao.imp.ShopDao;
import com.solt.tut2.entity.Shop;

public class ShopDaoTest {
	
	static EntityManagerFactory EMF;
	EntityManager em;
	Dao<Shop> dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EMF = Persistence.createEntityManagerFactory("jpa-tut2");

		// create account
		EntityManager em = EMF.createEntityManager();
		AccountDao acDao = new AccountDao(em);
		acDao.add(AccountDaoTest.getAccount());
		em.close();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EMF.close();
	}

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
		dao = new ShopDao(em);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void test1() {
		
		Shop shop = getShop();
		shop.addOwner(new AccountDao(em).findById("minlwin"));
		dao.add(shop);
		assertEquals(1, shop.getId());
	}

	@Test
	public void test2() {
		Shop shop = dao.findById(1L);
		assertNotNull(shop);
		assertEquals("Book Corner", shop.getName());
		assertEquals(1, shop.getOwners().size());
	}

	@Test
	public void test3() {
		// find
		List<Shop> list1 = dao.findAll();
		assertEquals(1, list1.size());
		
		String where = "t.name like :name";
		Map<String, Object> params = new HashMap<>();
		params.put("name", "Book%");
		
		List<Shop> list2 = dao.find(where, params);
		assertEquals(1, list2.size());
	}

	@Test
	public void test4() {
		// update
		Shop shop = dao.findById(1L);
		shop.setName("Stationary Queen");
		dao.update(shop);
		
		String where = "t.name like :name";
		Map<String, Object> params = new HashMap<>();
		params.put("name", "Book%");
		
		List<Shop> list2 = dao.find(where, params);
		assertEquals(0, list2.size());
	}

	@Test
	public void test5() {
		// delete
		Shop shop = dao.findById(1L);
		dao.delete(shop);
		
		assertNull(dao.findById(1L));
	}
	
	public static Shop getShop() {
		Shop shop = new Shop();
		shop.setName("Book Corner");
		return shop;
	}
}
