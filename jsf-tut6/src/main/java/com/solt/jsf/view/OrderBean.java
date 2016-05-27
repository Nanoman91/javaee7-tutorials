package com.solt.jsf.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.solt.jsf.entity.Bill;
import com.solt.jsf.entity.User;
import com.solt.jsf.model.OrderService;
import com.solt.jsf.model.UserService;

@Named
@ConversationScoped
public class OrderBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CartBean cart;
	
	@Inject
	private Conversation conversation;
	
	@Inject
	private OrderService model;
	@Inject
	private UserService userModel;
	
	@Inject
	private User user;
	
	@PostConstruct
	private void init() {
		if(cart.isLogin()) {
			user = cart.getUser();
		}
	}
	
	public String checkOut() {
		
		if(cart.getOrders().size() > 0) {
			
			if(conversation.isTransient()) {
				conversation.begin();
			}
			
			return "/cartView";
		}
		
		FacesMessage message = new FacesMessage(null, "Please select items to check out!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		
		return "/index";
	}
	
	public String confirm() {
		if(cart.isLogin()) {
			return "/confirm";
		}
		
		return "/addUser";
	}
	
	public String takeOrder() {
		// create user
		userModel.signIn(user);
		cart.setUser(user);

		Bill bill = cart.getBill();
		bill.setUser(user);
		bill.setOrders(cart.getOrders());
		
		// set bill to order
		cart.getOrders().forEach(a -> a.setBill(bill));
		model.addAll(bill.getOrders());
		
		// clear cart
		cart.initCart();
		
		if(!conversation.isTransient()) {
			conversation.end();
		}
		return "/myOrders";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
