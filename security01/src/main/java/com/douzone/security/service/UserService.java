package com.douzone.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.security.repository.UserRepository;
import com.douzone.security.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public boolean addUSer(UserVo vo) {
		return userRepository.addUser(vo);
	}

	public UserVo findByUsername(String userid) {
		return userRepository.findByUsername(userid);
	}
}
