package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JsonRrsult;
import com.douzone.mysite.service.GuestBookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller("GuestbookApiController")
@RequestMapping("/api/gb")
public class GuestBookController {
	
	@Autowired
	GuestBookService guestBookService;
	
	@ResponseBody
	@RequestMapping("/list")
	public JsonRrsult list(@RequestParam(value="sn", required=true, defaultValue="-1") Long no) {
		List<GuestbookVo> list = guestBookService.findAll(no);
		return JsonRrsult.success(list);
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public JsonRrsult add(@RequestBody GuestbookVo vo) {
		
		guestBookService.addGuestbook(vo);
		
		return JsonRrsult.success(vo);
	}
	
	@ResponseBody
	@RequestMapping("/delete/{no}")
	public JsonRrsult ex3(
			GuestbookVo vo,
			@PathVariable("no") Long no,
			@RequestParam("password") String password) {

		vo.setNo(no);
		vo.setPassword(password);
		boolean result = guestBookService.delete(vo);
		
		Long date = 0L;
		
		if(!result) {
			// 1. 삭제가 안된경우
			date = -1L;
		} else {
			// 2. 삭제가 된경우
			date = no;
		}
		
		return JsonRrsult.success(date);
	}
	
}
