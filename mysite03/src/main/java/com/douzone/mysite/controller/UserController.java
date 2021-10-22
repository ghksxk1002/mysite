package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value="/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method = RequestMethod.POST)
	public String join(UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	 
	@RequestMapping(value ="/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
//	@RequestMapping(value ="/logout", method = RequestMethod.GET)
//	public String logout(HttpSession session) {
//		session.removeAttribute("authUser");
//		session.invalidate();
//		return "redirect:/";
//	}
	
	// 스프링 인터셉터가 구연해주었다
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login1() {
//		
//		UserVo userVo = userService.getUser(email, password);
//		
//		// 로그인 성공 확인
//		if(userVo == null) {
//			model.addAttribute("result", "fail");
//			return "user/login";
//		}
//		
//		/*인증처리*/
//		session.setAttribute("authUser", userVo);
//		
		return "redirect:/";
	}
	
	@Auth
	@RequestMapping(value ="/update", method = RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
		// authUser-> 세션에 있는 유저 넘버를 가져오기 위해
		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo",userVo);
		
		return "user/update";
	}
	
	@RequestMapping(value ="/update", method = RequestMethod.POST)
	public String update(HttpSession session,UserVo userVo) {
		// 접근제어(Access Control List)
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		///////////////////////////////////////////////////////////
		userVo.setNo(authUser.getNo());
		userService.updateUser(userVo);

		authUser.setName(userVo.getName());
		
		return "redirect:/";
	}
}
