package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> findAll(Long nowPage) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "startIndex", (nowPage-1)*5 );
		return sqlSession.selectList( "board.findAll", map );
	}

	public Long findByListLength() {
		return sqlSession.selectOne("board.findByListLength");
	}

	public BoardVo findByTitleandContents(Long no) {
		return sqlSession.selectOne("board.findByTitleandContents", no);
	}

	public boolean insert(BoardVo vo) {
		return 1 == sqlSession.insert("board.insert",vo);
	}

	public boolean delete(Long no) {
		return 1 == sqlSession.delete("board.delete", no);
	}

	public boolean update(BoardVo vo) {
		return 1 == sqlSession.update("board.update", vo);
	}

	public boolean reply(BoardVo vo) {
		return 1 == sqlSession.update("board.reply", vo);
	}

	public BoardVo findGroupNo(Long no) {
		return sqlSession.selectOne("board.findGroupNo", no);
	}
	
	
}
