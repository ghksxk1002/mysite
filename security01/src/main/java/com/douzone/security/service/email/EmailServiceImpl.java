package com.douzone.security.service.email;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.douzone.security.vo.MailVo;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender mailSender;
	
	@Override
	public void sendMail(MailVo vo) {	// 보낼 이메일 내용이 들어오고
		
		
		try {
			MimeMessage msg = mailSender.createMimeMessage(); // 이메일 객체 생성
			// 수신자 이메일설정
			msg.addRecipient(RecipientType.TO, new InternetAddress(vo.getReceiveMail()));
			// 발신자 이메일주소, 이름
			msg.addFrom(new InternetAddress[] {
				new InternetAddress(vo.getSenderMail(), vo.getSenderName())
			});
			msg.setSubject(vo.getSubject(), "utf-8"); // 제목
			msg.setText(vo.getMessage(), "utf-8");	  // 내용
		
			mailSender.send(msg);					  // 이메일 발신
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("전송실패");
		}
	}

}
