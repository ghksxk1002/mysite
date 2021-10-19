package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserRepository;

@Service
public class UserService {
	
	// service에 레파지토리 주입
	@Autowired
	private UserRepository userRepository;

	
	
	
}
