package com.douzone.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session  = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser == null) {
			if(request.getParameter("no") == null) {
				MvcUtil.redirect(request.getContextPath() + "/bd", request, response);
			} else {
				MvcUtil.redirect(request.getContextPath() + "/bd?a=view&no=" + request.getParameter("no"), request, response);
			}
			return;
		}
		
		
		
		MvcUtil.forward("board/write", request, response);
		
	}

}
