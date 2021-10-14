package com.douzone.mysite.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeletAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 내가 찾으려고 하는 사용자 넘버
		String no =request.getParameter("no");
		Long no1 = Long.parseLong(no);
		String password = request.getParameter("password");
		
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no1);
		vo.setPassword(password);
		
		new GuestbookDao().delete(vo);

		MvcUtil.redirect("/mysite02/gb?a=index", request, response);
	}

}
