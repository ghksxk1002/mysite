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

public class DelAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		
		Long no = Long.parseLong(request.getParameter("no"));
		Long userNo = authUser.getNo();
		
		BoardVo vo = new BoardVo();
		vo.setUserNu(userNo);
		vo.setNo(no);
		
		new BoardDao().delete(vo);
		
		MvcUtil.redirect("/mysite02/bd?a=list", request, response);
	}

}
