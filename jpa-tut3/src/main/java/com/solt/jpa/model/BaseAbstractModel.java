package com.solt.jpa.model;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public abstract class BaseAbstractModel<T> implements BaseModel<T> {

	private Class<T> type;
	private EntityManager em;
	
	public BaseAbstractModel(Class<T> type, EntityManager em) {
		super();
		this.type = type;
		this.em = em;
	}

	private static final String SELECT = "select t from %s t ";
	
	
	@Override
	public void add(T t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
	}

	@Override
	public T findById(Object id) {
		return em.find(type, id);
	}

	@Override
	public List<T> findAll() {
		String sql = String.format(SELECT, type.getSimpleName());
		return em.createQuery(sql, type).getResultList();
	}

	@Override
	public List<T> find(String where, Map<String, Object> params) {
		String sql = String.format(SELECT, type.getSimpleName());
		
		// build dynamic sql
		if(null != where && null != params && 
				!where.isEmpty() && params.size() > 0) {
			sql = sql.concat("where ").concat(where);
		}
		
		TypedQuery<T> q = em.createQuery(sql, type);
		
		// set parameter to query
		if(null != where && null != params && 
				!where.isEmpty() && params.size() > 0) {
			
			for(String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		
		return q.getResultList();
	}

	@Override
	public void update(T t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
	}

	@Override
	public void delete(T t) {
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}

}
