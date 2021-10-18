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

public class SumitAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		Long no = authUser.getNo();
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		BoardVo boardVo = new BoardVo();
		boardVo.setTitle(title);
		boardVo.setContent(content);
		boardVo.setUserNu(no);

		new BoardDao().insert(boardVo);

		MvcUtil.redirect("/mysite02/bd?a=list", request, response);

	}

}
