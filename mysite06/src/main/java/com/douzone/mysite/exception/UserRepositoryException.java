package com.douzone.mysite.exception;

public class UserRepositoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UserRepositoryException(String message) {
		super(message);
	}
	
	public UserRepositoryException() {
		super("UserRepsoitory 예외 발생");
	}
	
}
