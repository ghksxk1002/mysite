package com.douzone.mysite.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;

	public List<GalleryVo> findAll(){
		return sqlSession.selectList("gallery.findAll");
	}

	public boolean insert(GalleryVo vo) {
		return 1 ==sqlSession.insert("gallery.insert", vo);
	}

	public boolean delete(Long no) {
		return 1 == sqlSession.delete("gallery.delete", no);
	}
	
}
