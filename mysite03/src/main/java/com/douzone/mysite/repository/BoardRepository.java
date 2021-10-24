package com.douzone.mysite.repository;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardRepository {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
}
