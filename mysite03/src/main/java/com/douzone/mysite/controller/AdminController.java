package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	SiteService siteService;

	@Auth(role="ADMIN")
	@RequestMapping("")
	public String main(Model model) {
		SiteVo siteVo = siteService.getSite();
		model.addAttribute(siteVo);
		return "admin/main";
	}
	
	@Auth(role="ADMIN")
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@Auth(role="ADMIN")
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@Auth(role="ADMIN")
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
}
