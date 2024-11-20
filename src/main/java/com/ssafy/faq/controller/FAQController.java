package com.ssafy.faq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.faq.model.FAQDto;
import com.ssafy.faq.model.service.FAQService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/faq")
@Tag(name="FAQ 컨트롤러", description = "FAQ에 관한 등록, 수정, 삭제, 목록, 상세 보기 등 FAQ 관련 작업을 처리하는 클래스입니다.")
public class FAQController {
	private final Logger log = LoggerFactory.getLogger(FAQController.class);
	@Autowired
	private FAQService faqService;
	
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> listFAQ(@RequestParam Map<String, String> map) {
		try {
			List<FAQDto> faqList = faqService.listArticle();
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("articles", faqList);
			return new ResponseEntity<>(dataMap, HttpStatus.OK);
		} catch (Exception e){
			log.error("FAQ 목록 조회 중 에러 발생: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> registerFAQ(@RequestBody FAQDto faqDto) {
		System.out.println("fd: " + faqDto);
		try {
			faqService.registerArticle(faqDto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("FAQ 목록 조회 중 에러 발생: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{articleNo}")
	public ResponseEntity<Map<String, Object>> getFAQ(@PathVariable int articleNo) {
		try {
			FAQDto article = faqService.getArticle(articleNo);
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("article", article);
			return new ResponseEntity<>(dataMap, HttpStatus.OK);
		} catch (Exception e) {
			log.error("FAQ 목록 조회 중 에러 발생: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{articleNo}")
	public ResponseEntity<Map<String, Object>> deleteFAQ(@PathVariable int articleNo) {
		try {
			faqService.deleteArticle(articleNo);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("FAQ 목록 조회 중 에러 발생: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/modify/{articleNo}")
	public ResponseEntity<Map<String, Object>> modifyFAQ(@RequestBody FAQDto faqDto, @PathVariable int articleNo) {
		System.out.println("modi: " + faqDto);
		try {
			faqService.modifyArticle(faqDto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("FAQ 목록 조회 중 에러 발생: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
