//package com.spring.bcjin.Email;
//
//import java.io.PrintWriter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Controller
//@EnableAsync
//public class MailController {
//	@Autowired
//	private MailService mailService;
//	
//	@RequestMapping(value="/sendMail.do", method=RequestMethod.GET)
//	public void sendSimpleMail(HttpServletResponse response, HttpServletRequest request) throws Exception {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter out = response.getWriter();
//		
//		// mailService의 sendMail() 메서드로 메일 관련 값(주소, 제목, 내용)을 전달함
//		mailService.sendMail("수신자@naver.com", "테스트메일", "안녕하세요. 테스트메일입니다.");
//		mailService.sendPreConfiguredMail("테스트 메일입니다.");
//		
//		out.print("메일을 보냈습니다.");
//	}
//}
