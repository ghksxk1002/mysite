package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifyformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session  = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath()+"/bd", request, response);
			return;
		}
		
		Long userNo = authUser.getNo();
		Long no = Long.parseLong(request.getParameter("no"));
		BoardVo boardVo = new BoardDao().findByPost(userNo, no);
		
		request.setAttribute("boardVo", boardVo);
		MvcUtil.forward("board/modify", request, response);
	}

}
