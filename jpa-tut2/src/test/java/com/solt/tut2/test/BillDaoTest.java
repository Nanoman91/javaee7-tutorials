package com.solt.tut2.test;

import static org.junit.Assert.assertEquals;
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
import com.solt.tut2.dao.imp.BillDao;
import com.solt.tut2.dao.imp.ItemDao;
import com.solt.tut2.dao.imp.ShopDao;
import com.solt.tut2.entity.Account;
import com.solt.tut2.entity.Bill;
import com.solt.tut2.entity.BillPK;
import com.solt.tut2.entity.Item;
import com.solt.tut2.entity.Order;
import com.solt.tut2.entity.Shop;

public class BillDaoTest {
	
	static EntityManagerFactory EMF;
	EntityManager em;
	Dao<Bill> dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EMF = Persistence.createEntityManagerFactory("jpa-tut2");
		EntityManager em = EMF.createEntityManager();
		initData(em);
	}
	
	public static void initData(EntityManager em) {
		AccountDao acDao = new AccountDao(em);

		// account for shop owner
		Account owner = AccountDaoTest.getAccount();
		acDao.add(owner);
		
		// account for buyer
		acDao.add(AccountDaoTest.getBuyer());
		
		// shop
		Shop shop = ShopDaoTest.getShop();
		shop.addOwner(owner);
		new ShopDao(em).add(shop);
		
		// items
		ItemDao itDao = new ItemDao(em);
		for(Item item : ItemDaoTest.getItems()) {
			item.setShop(shop);
			itDao.add(item);
		}
		
		em.close();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EMF.close();
	}

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
		dao = new BillDao(em);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}
	
	private static BillPK id;

	@Test
	public void test1() {
		// create
		Bill bill = new Bill();
		
		// shop
		Shop shop = new ShopDao(em).findById(1L);
		bill.setShop(shop);
		
		// buyer
		Account buyer = new AccountDao(em).findById("thidar");
		bill.setAccount(buyer);
		
		// orders
		String where = "t.shop = :shop";
		Map<String, Object> params = new HashMap<>();
		params.put("shop", shop);
		
		// get orders
		List<Item> items = new ItemDao(em).find(where, params);
		for(Item it : items) {
			Order od = new Order();
			od.setCount(2);
			od.setItem(it);
			bill.addOrder(od);
		}
		
		dao.add(bill);
		
		List<Bill> bills = dao.findAll();
		for(Bill b : bills) {
			id = b.getId();
		}
		
		assertEquals(500, bill.getTotal());
	}

	@Test
	public void test2() {
		// find by ID
		Bill bill = dao.findById(id);
		assertEquals(500, bill.getTotal());
		assertEquals("Thidar", bill.getAccount().getName());
	}

	@Test
	public void test3() {
		// find
		String where = "t.shop.id = :shop";
		Map<String, Object> params = new HashMap<>();
		params.put("shop", 1L);
		
		List<Bill> bills = dao.find(where, params);
		assertEquals(1, bills.size());
		
		Bill b = bills.get(0);
		assertEquals("Thidar", b.getAccount().getName());
		assertEquals(2, b.getOrders().size());
	}

	@Test
	public void test4() {
		// update
		Bill b = dao.findById(id);
		b.getOrders().remove(0);
		dao.update(b);
		
		b = dao.findById(id);
		assertEquals(300, b.getTotal());
	}

	@Test
	public void test5() {
		// delete
		dao.delete(dao.findById(id));
		assertNull(dao.findById(id));
	}
	
	public static Bill getBill() {
		Bill bill = new Bill();
		return bill;
	}
}
