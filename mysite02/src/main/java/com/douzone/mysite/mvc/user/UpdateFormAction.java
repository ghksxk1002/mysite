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

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 정보수정은 보안처리를 할수 있어야한다
		// Access Control
		// 로그인 핮 않은 사람은 들어올수 없다
		HttpSession session  = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		// 쿼리로 넘버 뺴옥;
		Long no = authUser.getNo();
		UserVo  userVo = new UserDao().findByNo(no);
		
		System.out.println(authUser);
		request.setAttribute("userVo", userVo);
		// return 으로 이 밑에 실행문은 실행되지 않는다.
		MvcUtil.forward("user/updateform", request, response);
	}

}
