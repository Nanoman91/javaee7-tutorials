package com.solt.jsf.model;

import java.util.List;

import javax.ejb.Local;

import com.solt.jsf.entity.Item;

@Local
public interface ItemService {
	List<Item> getAllItems();
}
