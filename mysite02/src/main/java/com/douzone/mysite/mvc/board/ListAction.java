package com.douzone.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int startPage = 1;
		int pageSize = 5;
		
		String nowPage = request.getParameter("pg");
		if(nowPage == null) {
			nowPage = "1";
		}
		// board의 총 길이
		BoardDao dao = new BoardDao();
		Long count = dao.findByListLength();
		
		Long lastPage = (count-1)/5+1; 
		
		if(Long.parseLong(nowPage) > lastPage) {
			request.setAttribute("startPage", startPage+5);
			request.setAttribute("pageSize", startPage+5);
		}
		
		
		List<BoardVo> list = dao.findAll(Integer.parseInt(nowPage));
		// 리스트에 답은 내용은 "list"라는 이름으로 request에 담아 놓는다
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("nowPage", nowPage);
		
		MvcUtil.forward("board/list", request, response);

	}

}
