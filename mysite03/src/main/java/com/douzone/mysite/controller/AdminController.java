package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.exception.GalleryServiceException;
import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	SiteService siteService;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo siteVo = siteService.getSite();
		model.addAttribute("siteVo", siteVo);
		return "admin/main";
	}
	
	@RequestMapping("/main/update")
	public String main(SiteVo site, @RequestParam("file") MultipartFile multipartFile) {
		try {
			String proflie = siteService.ImageUploader(site, multipartFile);
			site.setProfile(proflie);
		} catch(GalleryServiceException e) {
			System.out.println("이미지가 업로드 안됬으니까 정신차려");
		}
		
		siteService.update(site);
		servletContext.setAttribute("site", site);
		
		return "redirect:/admin";
		
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
