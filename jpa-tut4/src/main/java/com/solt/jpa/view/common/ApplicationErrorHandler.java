package com.solt.jpa.view.common;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@ErrorHandler
@Interceptor
public class ApplicationErrorHandler implements Serializable{

	private static final long serialVersionUID = 1L;

	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {
		
		try {
			return ic.proceed();
		} catch (ApplicationException e) {
			FacesMessage message = new FacesMessage("Application Error", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}

}
