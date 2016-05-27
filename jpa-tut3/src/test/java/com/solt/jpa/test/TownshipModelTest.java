package com.solt.jpa.test;

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

import com.solt.jpa.entity.Division;
import com.solt.jpa.entity.Township;
import com.solt.jpa.model.TownshipModel;

public class TownshipModelTest {
	
	static EntityManagerFactory emf;
	EntityManager em;
	TownshipModel model;
	
	@BeforeClass
	public static void init() {
		emf = Persistence.createEntityManagerFactory("jpa-tut3");
	}

	@Before
	public void setUp() throws Exception {
		em = emf.createEntityManager();
		model = new TownshipModel(em);
	}

	@Test
	public void test1() {
		Division d1 = new Division();
		d1.setName("Yangon");
		Division d2 = new Division();
		d2.setName("Mandalay");
		
		Township t1 = new Township();
		t1.setName("Kamayut");
		t1.setDivision(d1);
		
		Township t2 = new Township();
		t2.setName("San Chaung");
		t2.setDivision(d1);
		
		Township t3 = new Township();
		t3.setName("Chan Myae Thar Si");
		t3.setDivision(d2);
		
		model.add(t1);
		model.add(t2);
		model.add(t3);

		assertEquals(1, t1.getId());
		assertEquals(1, t1.getDivision().getId());
	}

	@Test
	public void test2() {
		Township t = model.findById(1);
		assertNotNull(t);
		assertEquals("Yangon", t.getDivision().getName());
		assertEquals("Kamayut", t.getName());
	}
	
	@Test
	public void test3() {
		
		List<Township> list1 = model.findAll();
		assertNotNull(list1);
		assertEquals(3, list1.size());
		
		String where = "t.division.name = :name";
		Map<String, Object> params = new HashMap<>();
		params.put("name", "Yangon");
		
		List<Township> list = model.find(where, params);
		assertNotNull(list);
		assertEquals(2, list.size());
	}
	
	@Test
	public void test4() {
		
		Township t = model.findById(1);
		t.setName("Thamine");
		
		model.update(t);
		
		assertEquals("Thamine", model.findById(1).getName());
	}
	
	@Test
	public void test5() {
		model.delete(model.findById(1));
		assertNull(model.findById(1));
	}
	
	
	@After
	public void tearDown() throws Exception {
		em.close();
	}
	
	@AfterClass
	public static void finishUp() {
		emf.close();
	}

}
