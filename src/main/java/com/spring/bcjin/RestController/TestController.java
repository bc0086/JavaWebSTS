package com.spring.bcjin.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

//@Controller
@RestController // JSP같은 뷰를 반환하는 것이 아니고, JSON이나 XML같은 데이터를 브라우저로 전송
@RequestMapping("/test/*")
public class TestController {
	static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello REST !";
	}
	
	@RequestMapping("/member")
	// MemberVO객체의 속성 값을 저장한 후 JSON으로 전송함
	public MemberVO member() {
		MemberVO vo = new MemberVO();
		vo.setId("hong");
		vo.setPwd("1234");
		vo.setName("홍길동");
		vo.setEmail("hong@test.com");
		return vo;
	}
	
	@RequestMapping("/membersList")
	// MemberVO객체를 저장할 ArrayList객체를 생성함
	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		for(int i=0; i<10; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("hong"+i);
			vo.setPwd("123"+i);
			vo.setName("홍길동"+i);
			vo.setEmail("hong"+i+"@test.com");
			list.add(vo);
		}
		return list;
			// ArrayList를 JSON으로 브라우저에 전송함
	}
	
	@RequestMapping("/membersMap")
	// MemberVO객체를 저장할 HashMap객체를 생성함
	public Map<Integer, MemberVO> membersMap() {
		Map<Integer, MemberVO> map = new HashMap<Integer, MemberVO>();
		for(int i=0; i<10; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("hong"+i);
			vo.setPwd("123"+i);
			vo.setName("홍길동"+i);
			vo.setEmail("hong"+i+"@test.com");
			map.put(i, vo);
		}
		return map;
			// HashMap객체를 브라우저에 전송함
	}
	
	// 브라우저에서 요청 시 {num} 부분의 값이 @PathVariable로 지정됨
	@RequestMapping(value= "/notice/{num}", method=RequestMethod.GET)
	public int notice(@PathVariable("num") int num) throws Exception {
		// 요청URL에서 지정된 값이 num에 자동으로 할당됨
		return num;
	}
	
	@RequestMapping(value="/info", method=RequestMethod.POST)
	public void modify(@RequestBody MemberVO vo) {
		// JSON으로 전송된 데이터를 MemberVO객체의 속성에 자동으로 설정해줌
		logger.info(vo.toString());
	}
	
	@RequestMapping(value="/res1")
	@ResponseBody // 메서드 호출 시 데이터를 전송하도록 설정함
	public Map<String, Object> res1() {
		Map<String, Object> map = new HashMap<String, Object>();
			// Map데이터를 브라우저로 전송
		map.put("id", "hong");
		map.put("name", "홍길동");
		return map; // return값은 맵의 데이터임
	}
	
	@RequestMapping(value="/res2")
	public ModelAndView res2() {
		return new ModelAndView("home"); // return값은 home.jsp
	}
	
	@RequestMapping("/membersList2")
	public ResponseEntity<List<MemberVO>> listMembers2() { // ResponseEntity로 응답함
		List<MemberVO> list = new ArrayList<MemberVO>();
		for (int i=0; i<10; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("lee" + i);
			vo.setPwd("123"+i);
			vo.setName("이순신" + i);
			vo.setEmail("lee" + i + "@test.com");
			list.add(vo);
		}
		return new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR); // 오류코드 500으로 응답함
	}
	
	@RequestMapping(value = "/res3")
	public ResponseEntity res3() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
			// 전송할 데이터의 종류와 인코딩을 설정함
		String message = "<script>";
		message += "alert('새 회원을 등록합니다.');";
		message += "location.href='/bcjin/test/membersList2';";
		message += "</script>";
		return new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			// ResponseEntity를 이용해 HTML형식으로 전송함
	}
}