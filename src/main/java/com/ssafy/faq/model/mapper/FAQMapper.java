package com.ssafy.faq.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.faq.model.FAQDto;

@Mapper
public interface FAQMapper {
	void registerArticle(FAQDto faqDto) throws Exception;
	List<FAQDto> listArticle() throws Exception;
	FAQDto getArticle(int articleNo) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	void modifyArticle(FAQDto faqDto) throws Exception;
}
