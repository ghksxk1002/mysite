package com.douzone.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 정보수정은 보안처리를 할수 있어야한다
		// Access Control
		// 로그인 핮 않은 사람은 들어올수 없다
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		////////////////////////////////////////////////////////////////
		Long no = authUser.getNo();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVo userVo = new UserVo();
		userVo.setNo(no);
		userVo.setName(name);
		userVo.setPassword(password);
		userVo.setGender(gender);
		
		new UserDao().update(userVo);
		authUser.setName(name);
		
		MvcUtil.redirect(request.getContextPath(), request, response);

	}

}
