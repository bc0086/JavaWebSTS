package com.spring.bcjin.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bcjin.member.vo.MemberVO;

@RestController // JSP같은 뷰를 반환하는 것이 아니고, JSON이나 XML같은 데이터를 브라우저로 전송
@RequestMapping("/test/*")
public class TestController {
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
}
