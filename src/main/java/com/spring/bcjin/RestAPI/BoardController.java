package com.spring.bcjin.RestAPI;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards") // 첫번째 단계의 요청명을 매핑
public class BoardController {
	static Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// get방식으로 요청하므로 모든 글 조회를 의미함
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<ArticleVO>> listArticles() {
		logger.info("listArticles 메서드 호출");
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		for(int i=0; i<10; i++) {
			ArticleVO vo = new ArticleVO();
			vo.setArticleNO(i);
			vo.setWriter("이순신" + i);
			vo.setTitle("안녕하세요" + i);
			vo.setContent("새 상품을 소개합니다." + i);
			list.add(vo);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// get방식으로 요청하면서 글 번호를 전달하므로 글 번호에 대한 글 조회를 의미함
	@RequestMapping(value="/{articleNO}", method=RequestMethod.GET)
	public ResponseEntity<ArticleVO> findArticle(@PathVariable("articleNO") Integer articleNO){
		logger.info("메서드 호출");
		ArticleVO vo = new ArticleVO();
		vo.setArticleNO(articleNO);
		vo.setWriter("홍길동");
		vo.setTitle("안녕하세요");
		vo.setContent("홍길동 글입니다.");
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	// post방식으로 요청하므로 요청 시 JSON으로 전달되는 객체를 새 글로 추가함
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<String> addArticle(@RequestBody ArticleVO articleVO) {
		ResponseEntity<String> resEntity = null;
		try {
			logger.info("addArticle 메서드 호출");
			logger.info(articleVO.toString());
			resEntity = new ResponseEntity<>("ADD_SUCCEEDED", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resEntity;
	}
	
	// put방식으로 요청하므로 articleNO에 대한 글을 전달되는 JSON정보로 수정
	@RequestMapping(value="/{articleNO}", method=RequestMethod.PUT)
	public ResponseEntity<String> modArticle(@PathVariable("articleNO") Integer articleNO, @RequestBody ArticleVO articleVO) {
		// 전송된 JSON 회원정보를 바로 ArticleVO객체의 속성에 설정
		
		ResponseEntity<String> resEntity = null;
		try {
			logger.info("modArticle호출");
			logger.info(articleVO.toString());
			resEntity = new ResponseEntity<>("MOD_SUCCEEDED", HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
	
	// delete방식으로 요청하므로 전달되는 articleVO에 대한 글을 삭제한다는 의미
	@RequestMapping(value="/{articleNO}", method=RequestMethod.DELETE)
	public ResponseEntity<String> removeArticle(@PathVariable("articleNO") Integer articleNO) {
		ResponseEntity<String> resEntity = null;
		try {
			logger.info("removeArticle호출");
			logger.info(articleNO.toString());
			resEntity = new ResponseEntity<>("REMOVE_SUCCEEDED", HttpStatus.OK);
		} catch (Exception e) {
			resEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
}
