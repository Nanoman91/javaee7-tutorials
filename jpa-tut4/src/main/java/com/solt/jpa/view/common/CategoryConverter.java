package com.solt.jpa.view.common;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.solt.jpa.entity.Category;
import com.solt.jpa.model.CategoryModel;

@Named
@RequestScoped
public class CategoryConverter implements Converter  {
	
	@Inject
	private CategoryModel model;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(null != value) {
			return model.getAll().stream().filter(a -> a.getCategory().equals(value)).findAny().get();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(null != value) {
			Category c = (Category) value;
			return c.getCategory();
		}
		return null;
	}


}
