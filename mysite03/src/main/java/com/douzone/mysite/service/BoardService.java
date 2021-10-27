package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	public Map<String, Object> getContentsList(Long nowPage) {

		// board의 총 길이
		Long count = boardRepository.findByListLength();

		Long lastPage = (count - 1) / 5 + 1;

		// 들어오는 값으로 그 페이지 블록찾기
		Long block = (nowPage + 4) / 5;
		Long start = (block - 1) * 5 + 1;
		Long end = block * 5;
		
		
		List<BoardVo> list = boardRepository.findAll(nowPage);
		// 리스트에 답은 내용은 "list"라는 이름으로 request에 담아 놓는다
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("list", list);
		map.put("count", count);
		map.put("lastPage", lastPage);
		map.put("nowPage", nowPage);
		map.put("start", start);
		map.put("end", end);
		return map;
	}

	public BoardVo showContents(Long no) {
		return boardRepository.findByTitleandContents(no);
	}

	public boolean write(BoardVo vo) {
		
		if(vo.getNo() != null) {
			BoardVo gn = boardRepository.findGroupNo(vo.getNo());
			System.out.println(gn);
			//return boardRepository.insert(ll);
		}
		
		return boardRepository.insert(vo);
	}
	
	public boolean delete(Long no) {
		return boardRepository.delete(no);
	}

	public boolean update(BoardVo vo) {
		return boardRepository.update(vo);
	}

}