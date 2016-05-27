package com.solt.jpa.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.solt.jpa.entity.Item;
import com.solt.jpa.entity.Security;
import com.solt.jpa.model.ItemModel;

public class ItemModelTest {
	
	private ItemModel model;
	private static EntityManagerFactory emf;
	
	@BeforeClass
	public static void beforeClass() {
		emf = Persistence.createEntityManagerFactory("jpa-tut1");
	}

	@Before
	public void setUp() throws Exception {
		EntityManager em = emf.createEntityManager();
		model = new ItemModel(em);
	}

	@Test
	public void test1() {
		
		Item item = new Item();
		item.setName("Pencil");
		item.setPrice(200);
		
		Security security = new Security();
		security.setUserId(1);
		item.setSecurity(security);
		
		model.insert(item);
	}

	@Test
	public void test2() {
		
		Item item = model.findById(1);
		assertNotNull(item);
		assertEquals(1, item.getId());
		assertEquals("Pencil", item.getName());
		assertEquals(200, item.getPrice());
	}
}
