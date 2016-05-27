package com.solt.jsf.view.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named
@RequestScoped
public class DobConverter implements Converter {
	
	private DateFormat df;
	
	@PostConstruct
	private void init() {
		df = new SimpleDateFormat("yyyy-MM-dd");
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		try {
			if(null != value) {
				return df.parse(value);
			}
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Format Error", "Please check your date format (yyyy-MM-dd)!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if(null != value && value instanceof Date) {
			return df.format((Date)value);
		}
		return null;
	}

}
