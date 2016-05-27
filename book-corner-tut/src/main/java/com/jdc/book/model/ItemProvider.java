package com.jdc.book.model;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.jdc.book.entity.Item;

@ApplicationScoped
public class ItemProvider {

	@Named
	@Produces
	private List<Item> items;
	
	@PostConstruct
	private void init() {
		items = Arrays.asList(
				new Item("Book", 500), 
				new Item("Pencil", 100), 
				new Item("Dictionary", 5000), 
				new Item("Ruler", 200), 
				new Item("Compass", 1000), 
				new Item("Clear File", 100), 
				new Item("Clip", 300), 
				new Item("Eraser", 150)
				);
	}

}
