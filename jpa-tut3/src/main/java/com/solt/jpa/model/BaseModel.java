package com.solt.jpa.model;

import java.util.List;
import java.util.Map;

public interface BaseModel<T> {

	void add(T t);

	T findById(Object id);

	List<T> findAll();

	List<T> find(String where, Map<String, Object> params);

	void update(T t);

	void delete(T t);

}