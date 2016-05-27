package com.solt.tut2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
import com.solt.tut2.dao.imp.BillDao;
import com.solt.tut2.dao.imp.ItemDao;
import com.solt.tut2.dao.imp.OrderDao;
import com.solt.tut2.dao.imp.ShopDao;
import com.solt.tut2.entity.Bill;
import com.solt.tut2.entity.Item;
import com.solt.tut2.entity.Order;

public class OrderDaoTest {
	
	static EntityManagerFactory EMF;
	EntityManager em;
	Dao<Order> dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EMF = Persistence.createEntityManagerFactory("jpa-tut2");
		BillDaoTest.initData(EMF.createEntityManager());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EMF.close();
	}

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
		dao = new OrderDao(em);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void test1() {
		// create
		Bill b = new Bill();
		b.setShop(new ShopDao(em).findById(1L));
		b.setAccount(new AccountDao(em).findById("thidar"));
		
		BillDao bDao = new BillDao(em);
		bDao.add(b);
		
		String where = "t.account.loginId = :login";
		Map<String, Object> params = new HashMap<>();
		params.put("login", "thidar");
		List<Bill> bills = bDao.find(where, params);
		
		b = bills.get(0);
		
		List<Item> items = new ItemDao(em).findAll();
		for(Item it : items) {
			Order od = new Order();
			od.setItem(it);
			od.setCount(2);
			od.setBill(b);
			
			dao.add(od);
			
			assertTrue(od.getId() > 0);
		}
		
		
	}

	@Test
	public void test2() {
		// find by ID
		Order od = dao.findById(1L);
		assertNotNull(od);
		assertEquals(200, od.getTotal());
	}

	@Test
	public void test3() {
		// find
		assertEquals(2, dao.findAll().size());
		
		String where = "t.item.price <= :price";
		Map<String, Object> params = new HashMap<>();
		params.put("price", 100);
		
		assertEquals(1, dao.find(where, params).size());
	}

	@Test
	public void test4() {
		// update
		Order od = dao.findById(1L);
		od.setCount(5);
		dao.update(od);
		
		for(Bill b : new BillDao(em).findAll()) {
			assertEquals(800, b.getTotal());
		}
	}

	@Test
	public void test5() {
		// delete
		dao.delete(dao.findById(1L));
		assertNull(dao.findById(1L));
	}
}
