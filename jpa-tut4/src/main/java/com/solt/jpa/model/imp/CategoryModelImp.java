package com.solt.jpa.model.imp;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.solt.jpa.dao.imp.CategoryDao;
import com.solt.jpa.entity.Category;
import com.solt.jpa.model.CategoryModel;

@Local
@Stateless
public class CategoryModelImp implements CategoryModel {

	@Inject
	private CategoryDao catDao;

	@Override
	public List<Category> getAll() {
		return catDao.select(null, null);
	}

}