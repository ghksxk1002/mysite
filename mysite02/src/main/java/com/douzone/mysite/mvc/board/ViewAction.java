package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 내가 찾으려고 하는 게시물넘버 받기http://localhost:8080/mysite02/bd?a=view
		Long no = Long.parseLong(request.getParameter("no"));
		
		new BoardDao().updateHit(no);
		BoardVo boardVo = new BoardDao().findByTitleandContents(no);
		request.setAttribute("boardVo", boardVo);
		

		MvcUtil.forward("board/view", request, response);

	}

}
