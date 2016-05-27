package com.jdc.book.utils;

public class ShopException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ShopException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShopException(String message) {
		super(message);
	}

	public ShopException(Throwable cause) {
		super(cause);
	}

	
}
