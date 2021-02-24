package com.spring.bcjin.HtmlEmail;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@EnableAsync
public class MailController {
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value="/sendMail.do", method=RequestMethod.GET)
	public void sendSimpleMail(HttpServletResponse response, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		StringBuffer sb = new StringBuffer(); // StringBuffer sb 변수 선언
		sb.append("<html><body>");
 		sb.append("<meta http-equiv='Content-Type' content='text/html; charset=euc-kr'>");
 		sb.append("<h1>"+"제품소개"+"<h1><br>");
 		sb.append("신간 도서를 소개합니다.<br><br>");
 		sb.append("<a href='http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9788956746425&orderClick=LAG&Kc=#N'>");
 		sb.append("<img  src='http://image.kyobobook.co.kr/images/book/xlarge/425/x9788956746425.jpg' /> </a><br>");
 		sb.append("</a>");
 		sb.append("<a href='http://www.kyobobook.co.kr/product/detailViewKor.laf?ejkGb=KOR&mallGb=KOR&barcode=9788956746425&orderClick=LAG&Kc=#N'>상품보기</a>");
 		sb.append("</body></html>"); // 문자열로 HTML태그를 작성한 후 sb에 저장함
 		
 		String str=sb.toString(); // 문자열로 변환함
		
		mailService.sendMail("수신자@naver.com", "신상품을 소개합니다.", str);
			// HTML 형식의 내용을 메일로 전송함.
		
		out.print("메일을 보냈습니다.");
	}
}
