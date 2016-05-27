package com.solt.jsf.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.solt.jsf.entity.Item;
import com.solt.jsf.model.ItemService;

public class ItemBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Named
	@Produces
	private List<Item> items;
	
	@Inject
	private ItemService service;
	
	@PostConstruct
	private void init() {
		items = service.getAllItems();
	}

}
