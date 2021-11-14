package com.douzone.mysite.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.GuestbookRepositoryException;
import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> selectAll() throws GuestbookRepositoryException{
		return sqlSession.selectList("guestbook.findAll");
	}
	// API
	public List<GuestbookVo> selectAll(Long no) {
		return sqlSession.selectList("guestbook.findAll", no);
	}
	
	public boolean delete(GuestbookVo vo) {
		int count = sqlSession.delete("guestbook.delete", vo);
		return count == 1;		
	}
	
	public boolean insert(GuestbookVo vo) {
		int count = sqlSession.insert("guestbook.insert", vo);
		return count == 1;
	}

	
	
	
}