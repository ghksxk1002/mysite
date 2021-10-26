package com.douzone.mysite.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String index(
			@RequestParam(value = "pager", required = true, defaultValue = "1") Long nowPage
			, Model model) {
		Map<String, Object> map = boardService.getContentsList(nowPage);
		//model.addAttribute("authUser", authUser.getNo());
		model.addAttribute("map", map);
		return "board/index";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, BoardVo vo) {
		vo.setUserNo(authUser.getNo());
		boardService.write(vo);
		
		return "redirect:/board";
	}
	
	// 답글
	@Auth
	@RequestMapping(value="/write/{no}", method=RequestMethod.GET)
	public String write(
			@AuthUser UserVo authUser,
			@PathVariable("no") Long no,
			BoardVo vo) {
		vo.setUserNo(authUser.getNo());
		boardService.write(vo);
		
		return "redirect:/board";
	}
	
	
	@Auth
	@RequestMapping(value="/delete/{no}", method = RequestMethod.GET)
	public String delete(
			@PathVariable("no") Long no) {
		boardService.delete(no);
		return "redirect:/board";
	}
	
	@RequestMapping("/view/{no}")
	public String view(
			@PathVariable("no") Long no,
			@AuthUser UserVo authUser,
			Model model) {
		BoardVo vo = boardService.showContents(no);
		vo.setUserNo(authUser.getNo());
		System.out.println("userNo:"+authUser.getNo());
		
		model.addAttribute("vo", vo);
		return "board/view";
	}
	
	
	
	
}