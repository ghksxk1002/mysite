package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobleExceptionHendler {
	
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
	
		// 원래하는 방법 지금은 못함
		//LOGGER.error(errors.toString());
	
		
		// 2. 요청 구분
		request.setAttribute("exception", errors.toString());
		
		// 3. 사과페이지(사과 페이지로 가면 정상적으로 종료것이다)
		request
			.getRequestDispatcher("/WEB-INF/views/error/exception.jsp")
			.forward(request, response);
		
	
	}
}
