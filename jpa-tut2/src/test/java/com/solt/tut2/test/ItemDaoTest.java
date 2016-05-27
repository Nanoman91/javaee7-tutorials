package com.solt.tut2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
import com.solt.tut2.dao.imp.ItemDao;
import com.solt.tut2.dao.imp.ShopDao;
import com.solt.tut2.entity.Account;
import com.solt.tut2.entity.Item;
import com.solt.tut2.entity.Item.Status;
import com.solt.tut2.entity.Shop;

public class ItemDaoTest {
	
	static EntityManagerFactory EMF;
	EntityManager em;
	Dao<Item> itemDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EMF = Persistence.createEntityManagerFactory("jpa-tut2");
		EntityManager em = EMF.createEntityManager();
		
		Account ac = AccountDaoTest.getAccount();
		new AccountDao(em).add(ac);
		
		Shop shop = ShopDaoTest.getShop();
		shop.addOwner(ac);
		new ShopDao(em).add(shop);
		
		em.close();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EMF.close();
	}

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
		itemDao = new ItemDao(em);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void test1() {
		
		Shop shop = new ShopDao(em).findById(1L);
		
		// create
		for(Item i : getItems()) {
			i.setShop(shop);
			itemDao.add(i);
			assertTrue(i.getId() > 0);
		}
	}

	@Test
	public void test2() {
		// find by id
		Item item = itemDao.findById(1L);
		assertNotNull(item);
		assertEquals("Book", item.getName());
		assertEquals("book.jpg", item.getPhoto());
		assertEquals(100, item.getPrice());
		assertEquals(Status.InStock, item.getStatus());
		
	}

	@Test
	public void test3() {
		// find
		List<Item> list1 = itemDao.findAll();
		assertNotNull(list1);
		assertEquals(2, list1.size());
		
		String where = "t.price < :price";
		Map<String, Object> params = new HashMap<>();
		params.put("price", 150);
		
		List<Item> list2 = itemDao.find(where, params);
		assertNotNull(list2);
		assertEquals(1, list2.size());
		
		String where1 = "t.price >= :price";
		
		List<Item> list3 = itemDao.find(where1, params);
		assertNotNull(list3);
		assertEquals(1, list3.size());
		
	}

	@Test
	public void test4() {
		// update
		String where = "t.status = :s";
		Map<String, Object> params = new HashMap<>();
		params.put("s", Status.InStock);
		
		List<Item> list1 = itemDao.find(where, params);
		assertEquals(2, list1.size());
		
		Item i = list1.get(1);
		i.setStatus(Status.SoldOut);
		itemDao.update(i);
		
		List<Item> list2 = itemDao.find(where, params);
		assertEquals(1, list2.size());
	}

	@Test
	public void test5() {
		Item item = itemDao.findById(1L);
		itemDao.delete(item);
		assertEquals(1, itemDao.findAll().size());
	}
	
	public static List<Item> getItems() {
		
		List<Item> list = new ArrayList<>();
		Item item = new Item();
		item.setName("Book");
		item.setPhoto("book.jpg");
		item.setPrice(100);
		item.setStatus(Status.InStock);

		list.add(item);

		Item item2 = new Item();
		item2.setName("Erasor");
		item2.setPhoto("erasor.jpg");
		item2.setPrice(150);
		item2.setStatus(Status.InStock);

		list.add(item2);
		
		return list;
	}

}
