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

		String page = request.getParameter("pg");
		if(page == null) {
			page = "1";
		}
		BoardDao dao = new BoardDao();
		
		List<BoardVo> list = dao.findAll(Integer.parseInt(page));
		// 리스트에 답은 내용은 "list"라는 이름으로 request에 담아 놓는다
		
		request.setAttribute("list", list);
		request.setAttribute("pg", page);
		
		
		MvcUtil.forward("board/list", request, response);

	}

}
