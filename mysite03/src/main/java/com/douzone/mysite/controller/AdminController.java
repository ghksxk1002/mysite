package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	SiteService siteService;

	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo siteVo = siteService.getSite();
		System.out.println(siteVo);
		model.addAttribute("siteVo", siteVo);
		return "admin/main";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
}
