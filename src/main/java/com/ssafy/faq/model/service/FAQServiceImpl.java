package com.ssafy.faq.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.faq.model.FAQDto;
import com.ssafy.faq.model.mapper.FAQMapper;

@Service
public class FAQServiceImpl implements FAQService {
	
	@Autowired
	private FAQMapper mapper;

	@Override
	public void registerArticle(FAQDto faqDto) throws Exception {
		mapper.registerArticle(faqDto);
	}

	@Override
	public List<FAQDto> listArticle() throws Exception {
		return mapper.listArticle();
	}

	@Override
	public FAQDto getArticle(int articleNo) throws Exception {
		return mapper.getArticle(articleNo);
	}

	@Override
	public void deleteArticle(int articleNo) throws Exception {
		mapper.deleteArticle(articleNo);
	}

	@Override
	public void modifyArticle(FAQDto faqDto) throws Exception {
		// TODO Auto-generated method stub
		mapper.modifyArticle(faqDto);
	}

}
