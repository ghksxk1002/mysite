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

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 뭘 받아야할까?
		 * 
		 *  그럼 부모글의 그룹넘버를 set 하고
		 *  부모글의 order no +1 set 하고
		 *  부모글의 depth no+1 set 하고
		 *  그리고 글쓴이 정보들로
		 *  insert 하면 됨
		 *  그리고 리다이렉트
		 *  끝   */
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			MvcUtil.redirect(request.getContextPath() + "/bd" , request, response);
			return;
		}
		
		Long uesrNo = authUser.getNo();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		Long no = Long.parseLong(request.getParameter("no"));
		BoardVo finedNo = new BoardDao().findByNo(no);
		
		Long finedNoGroupNo = finedNo.getGroupNu();
		Long finedNoOrderNo = finedNo.getOrderNu();
		Long finedNoDepthNo = finedNo.getDepthNu();

		BoardVo boardVo = new BoardVo();
		boardVo.setTitle(title);
		boardVo.setContent(content);
		boardVo.setUserNu(uesrNo);
		boardVo.setGroupNu(finedNoGroupNo);
		boardVo.setOrderNu(finedNoOrderNo);
		boardVo.setDepthNu(finedNoDepthNo);
		
		new BoardDao().updateOrderNo(boardVo);
		new BoardDao().replay(boardVo);

		MvcUtil.redirect(request.getContextPath() + "/bd", request, response);	
		
	}

}
