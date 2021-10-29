package com.douzone.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

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
			@RequestParam(value = "pager", required = true, defaultValue = "1") Long nowPage,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String kwd,
			@AuthUser UserVo authUser,
			Model model) {
		Map<String, Object> map = boardService.getContentsList(nowPage, kwd);
		model.addAttribute("map", map);
		return "board/index";
	}
	
	@RequestMapping("/view/{no}")
	public String view(
			@PathVariable("no") Long no,
			Model model) {
		BoardVo vo = boardService.showContents(no);
		System.out.println(vo);
		model.addAttribute("vo", vo);
		
		return "board/view";
	}
	
	// 글수정
	@Auth
	@RequestMapping("/modify/{no}")
	public String modify(
			@PathVariable("no") Long no,
			Model model) {
		BoardVo vo = boardService.showContents(no);
		model.addAttribute("vo",vo);
		return "board/modify";
	}
	
	@Auth
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String modify(BoardVo vo) {
		boardService.update(vo);
		return "redirect:/board";
	}
	
	
	@Auth
	@RequestMapping(value={"/write", "/write/{no}"}, method=RequestMethod.GET)
	public String write(@PathVariable(value = "no", required = false) Long no, Model model, BoardVo vo){
		if(no != null) {
			model.addAttribute("no", no);
		}
		System.out.println("writeFron:"+vo);
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, BoardVo vo) {
		
		System.out.println(vo);
		vo.setUserNo(authUser.getNo());
		
		boardService.write(vo);
		
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping(value="/delete/{no}", method = RequestMethod.GET)
	public String delete(
			@PathVariable("no") Long no) {
		return "redirect:/board";
	}
}