package com.solt.jpa.test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.solt.jpa.entity.Address;
import com.solt.jpa.entity.Division;
import com.solt.jpa.entity.Township;
import com.solt.jpa.entity.User;

import com.solt.jpa.model.BaseModel;
import com.solt.jpa.model.TownshipModel;
import com.solt.jpa.model.UserModel;

public class UserModelTest {
	
	static EntityManagerFactory EMF;
	EntityManager em;
	BaseModel<User> model;
	BaseModel<Township> tshModel;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EMF = Persistence.createEntityManagerFactory("jpa-tut3");

		// create divisions
		Division d1 = new Division();
		d1.setName("Yangon");
		
		Division d2 = new Division();
		d2.setName("Mandalay");
		
		// create townships
		Township t1 = new Township();
		t1.setDivision(d1);
		t1.setName("Kamayut");
		
		Township t2 = new Township();
		t2.setDivision(d1);
		t2.setName("Sanchaung");
		
		Township t3 = new Township();
		t3.setName("Chan Myae Tharsi");
		t3.setDivision(d2);
		
		TownshipModel tshModel = new TownshipModel(EMF.createEntityManager());
		
		tshModel.add(t1);
		tshModel.add(t2);
		tshModel.add(t3);	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EMF.close();
	}

	@Before
	public void setUp() throws Exception {
		em = EMF.createEntityManager();
		model = new UserModel(em);
		tshModel = new TownshipModel(em);
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void test1() throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		// creation
		User u1 = new User();
		Address a1 = new Address();
		a1.setTownship(tshModel.findById(1));
		a1.setAddress("No 33, Sanyeik Nyein 3 Street");
		
		u1.setAddress(a1);
		u1.setName("Aung Aung");
		u1.setLoginId("aung");
		u1.setDob(df.parse("1990-10-01"));
		
		model.add(u1);
		
		u1 = model.findById("aung");
		assertNotNull(u1);
		assertEquals("Aung Aung", u1.getName());
		
		User u2 = new User();
		Address a2 = new Address();
		a2.setTownship(tshModel.findById(3));
		a2.setAddress("No 33, Sanyeik Nyein 3 Street");
		
		u2.setAddress(a2);
		u2.setName("Maung Maung");
		u2.setLoginId("maung");
		u2.setDob(df.parse("1990-10-03"));
		
		model.add(u2);
	}

	@Test
	public void test2() {
		User u = model.findById("maung");
		assertNotNull(u);
		assertEquals("Mandalay", u.getAddress().getTownship().getDivision().getName());;
	}
	
	@Test
	public void test3() {
		// find where
		assertEquals(2, model.findAll().size());
		
		String where = "t.address.township.division.name = :d";
		Map<String, Object> params = new HashMap<>();
		params.put("d", "Yangon");
		
		assertEquals(1, model.find(where, params).size());
	}
	@Test
	public void test4() {
		// update
		User u = model.findById("aung");
		u.setName("Aung Aung Oo");
		model.update(u);
		
		assertEquals("Aung Aung Oo", model.findById("aung").getName());
	}
	@Ignore
	@Test
	public void test5() {
		// delete
		model.delete(model.findById("aung"));
		assertNull(model.findById("aung"));
		assertEquals(1, model.findAll().size());
	}
}
