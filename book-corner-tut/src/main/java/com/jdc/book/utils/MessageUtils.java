package com.jdc.book.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageUtils {

	public static void addMessage(String message) {
		FacesMessage msg = new FacesMessage(null, message);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
