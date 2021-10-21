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

		String nowPage = request.getParameter("pg");

		if (nowPage == null) {
			nowPage = "1";
		}
		BoardDao dao = new BoardDao();
		// board의 총 길이
		Long count = dao.findByListLength();

		Long lastPage = (count - 1) / 5 + 1;
		
		// 들어오는 값으로 그 페이지 블록찾기
		int block = (Integer.parseInt(nowPage)+4)/5;
		int start = (block - 1) * 5 + 1;
		int end = block * 5;

		List<BoardVo> list = dao.findAll(Integer.parseInt(nowPage));
		// 리스트에 답은 내용은 "list"라는 이름으로 request에 담아 놓는다

		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("start", start);
		request.setAttribute("end", end);

		System.out.println("..."+block);	
		
		MvcUtil.forward("board/list", request, response);

	}

}
