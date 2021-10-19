package com.douzone.mysite.exeption;

public class UserRepositoryExction extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UserRepositoryExction(String message) {
		super(message);
	}
	
	public UserRepositoryExction() {
		super("UserRepsoitory 예외 발생");
	}
	
}
