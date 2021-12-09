package com.douzone.security.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.security.dto.JsonResult;
import com.douzone.security.service.UserService;
import com.douzone.security.vo.UserVo;



@RestController("userApiController")
@RequestMapping("/user/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/checkemail")
	public JsonResult checkid(@RequestParam(value="id") String id) {
		String userid = id;
		UserVo userVo = userService.findByUsername(userid);
		
		return JsonResult.success(userVo != null);
	}
}