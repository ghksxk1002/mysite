package com.douzone.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.security.service.email.EmailService;
import com.douzone.security.vo.MailVo;

@Controller
@RequestMapping("/email")
public class EmailController {
	@Autowired
	private EmailService emailService;
	
	@RequestMapping({"", "/"})
	public String index() {
		return "email/index";
	}
	
	@RequestMapping("/send")
	public String send(MailVo vo, Model model) {
		try {
			System.out.println(vo);
			emailService.sendMail(vo);
			model.addAttribute("message", "이메일이발송되었습니다");
			
		} catch (MailSendException e) {
			e.printStackTrace();
			System.out.println("전송실패");
			model.addAttribute("message", "이메일 전송 실패....");
		}
		return "redirect:/email";
	}
}
