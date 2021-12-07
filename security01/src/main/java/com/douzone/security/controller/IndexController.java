package com.douzone.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.security.service.UserService;
import com.douzone.security.vo.UserVo;

@CrossOrigin
@Controller
public class IndexController {
	
	@Autowired
	UserService userService;
	
	// 의존성 주입하고 사용
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping({"","/"})
	public String index() {
		return "index";
	}
	

	@GetMapping("/user")
	public String user() {
		return "ok";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	
	// 지금은 해당 url을 스프링 시큐리티가 가로체채고 있다 - SecurityConfig 파일이 작동을 하지 않아서 이 메소드로 올수 있다
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}

	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@PostMapping("/join")
	public String join(UserVo vo) {
		
		System.out.println("helleuser : " +vo);
		vo.setRole("ROLE_ USER");
		
		vo.setPassword(bCryptPasswordEncoder.encode(vo.getPassword()));
		
		userService.addUSer(vo);
		
		System.out.println("[userVo]:"+vo);
		return "redirect:/loginForm";
	}
}
