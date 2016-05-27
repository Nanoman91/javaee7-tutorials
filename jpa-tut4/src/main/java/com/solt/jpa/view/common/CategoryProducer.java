package com.solt.jpa.view.common;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.solt.jpa.entity.Category;
import com.solt.jpa.model.CategoryModel;

public class CategoryProducer {

    private List<Category> allCategory;

    @Inject
    private CategoryModel catModel;

    @PostConstruct
    private void init() {
    	allCategory = catModel.getAll();
    }

    @Named
    @Produces
	public List<Category> getAllCategory() {
		return allCategory;
	}
}