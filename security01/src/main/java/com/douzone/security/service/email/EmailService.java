package com.douzone.security.service.email;

import com.douzone.security.vo.MailVo;

public interface EmailService {
	void sendMail(MailVo vo);
}
