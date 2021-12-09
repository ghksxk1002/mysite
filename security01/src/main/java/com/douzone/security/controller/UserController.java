package com.douzone.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.douzone.security.service.UserService;
import com.douzone.security.vo.UserVo;

@CrossOrigin
@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	// 의존성 주입하고 사용
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping({"","/"})
	public String index() {
		return "main/index";
	}
	
	// 지금은 해당 url을 스프링 시큐리티가 가로체채고 있다 - SecurityConfig 파일이 작동을 하지 않아서 이 메소드로 올수 있다
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/login";
	}
	
	@GetMapping("/loginsuccess")
	public String loginsuccess() {
		return "user/loginsuccess";
	}
	@GetMapping("/post")
	public String post() {
		return "user/post";
	}

	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/join";
	}
	
	@PostMapping("/join")
	public String join(
			@ModelAttribute @Valid UserVo vo, 
			BindingResult result, 
			Model model) {
		
		System.out.println("helleuser : " +vo);
		// 발리데이션을 위한 설정
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		
		System.out.println(vo);
		vo.setPassword(bCryptPasswordEncoder.encode(vo.getPassword()));
		
		userService.addUSer(vo);
		
		System.out.println("[userVo]:"+vo);
		return "redirect:/loginForm";
	}
	
}
