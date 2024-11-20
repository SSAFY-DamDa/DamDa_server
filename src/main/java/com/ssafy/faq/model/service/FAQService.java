package com.ssafy.faq.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.faq.model.FAQDto;

public interface FAQService {
	void registerArticle(FAQDto faqDto) throws Exception;
	List<FAQDto> listArticle() throws Exception;
	FAQDto getArticle(int articleNo) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	void modifyArticle(FAQDto faqDto) throws Exception;
}
