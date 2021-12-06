package com.douzone.security.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.security.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlsession;

	public boolean addUser(UserVo vo) {
		return 1 == sqlsession.insert("user.addUser", vo);
	}

	public UserVo findByUsername(String username) {
		System.err.println("여기는 레파지토리");
		return sqlsession.selectOne("user.findByUsername", username);
	}
	
	
}	
