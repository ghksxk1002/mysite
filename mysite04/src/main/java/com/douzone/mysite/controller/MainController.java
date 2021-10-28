package com.douzone.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.vo.UserVo;

@Controller
public class MainController {
	
	@Auth
	@RequestMapping({"", "/"})
	public String index(@AuthUser UserVo userVo) {
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/msg01")
	public String message01() {
		// 한글이 깨지지 않도록 bean 설정을 통해 메세지 컴버터를 통한 
		// 응답도 한글로 나오게 servlet 설정을 해야한다
		return "안녕"; 
	}
	
	@ResponseBody
	@RequestMapping("/msg02")
	public Object message02(/*HttpServletResponse resp*/) throws Exception {
		//resp.setContentType("application/json; charset=UTF-8");
		//resp.getWriter().print("{\"message\":\"Hello World\"}");
		Map<String, Object> map = new HashMap<>();
		map.put("message", "Hello World");
		
		return map;
	}
}
