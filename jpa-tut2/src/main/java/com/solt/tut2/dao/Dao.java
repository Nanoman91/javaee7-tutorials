package com.solt.tut2.dao;

import java.util.List;
import java.util.Map;

public interface Dao<T> {

	void add(T t);

	T findById(Object id);

	List<T> findAll();

	List<T> find(String where, Map<String, Object> params);

	void update(T t);

	void delete(T t);

}