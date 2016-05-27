package com.solt.jpa.dao;

import java.io.Serializable;
import java.util.*;

public interface Dao<T> extends Serializable {

    public void insert(T t);

    public void update(T t);

    public void delete(T t);

    public List<T> select(String where, Map<String, Object> params);

    public T findById(Object id);

}