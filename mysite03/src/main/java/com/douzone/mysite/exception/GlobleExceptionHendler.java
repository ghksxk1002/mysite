package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.douzone.mysite.dto.JsonRrsult;

@ControllerAdvice
public class GlobleExceptionHendler {
	private static final Log LOGGER = LogFactory.getLog(GlobleExceptionHendler.class);
	
	@ExceptionHandler(Exception.class)
	public void HandlerException(
			HttpServletRequest request,
			HttpServletResponse response,
			Exception e) throws Exception{
		
		// 1. 로깅
		// 버퍼를 가지고있는
		// String 이기 때문에  jsp로 보낸다
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		LOGGER.error(errors.toString());
	
		
		// 2. 요청 구분
		JsonRrsult.fail(errors.toString());
		// 3. 사과페이지(사과 페이지로 가면 정상적으로 종료것이다)
		request.setAttribute("exception", errors.toString());
		request
			.getRequestDispatcher("/WEB-INF/views/error/exception.jsp")
			.forward(request, response);
		
	
	}
}
