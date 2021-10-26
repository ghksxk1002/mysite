package com.douzone.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
	public String join(@Valid UserVo vo, BindingResult result) {
		
		// 발리드 값 셋팅
		// 1. reuslt에 에러가 있느닞 확인
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for(ObjectError error : list) {
				// 출력되는 에러내용을 사용자에게 알려주기위해 join으로 돌려주어야 한다
				// 그러기 위해서는 result 안에 리스트로 들어가 있는 에러내용을 뽑아내야 한는데
				// 스프링에서 태그를 지원해준다 jsp로 가자 고고
				System.err.println(error);
				
				
			}
			
			// 에러가 있으면 조인으로 돌려야됨
			return "user/join";
		}
		
		
		//userService.join(vo);
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

	
	@Auth
	@RequestMapping(value ="/update", method = RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
	
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
