package com.ssafy.faq.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "FAQDto : FAQ 정보", description = "FAQ의 상세 정보를 나타낸다.")
public class FAQDto {
	private int articleNo;
	private String userId;
	private String subject;
	private String content;
	private String registerTime;
	private List<FileInfoDto> fileInfos;

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public List<FileInfoDto> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfoDto> fileInfos) {
		this.fileInfos = fileInfos;
	}

	@Override
	public String toString() {
		return "FAQDto [articleNo=" + articleNo + ", userId=" + userId + ", subject=" + subject + ", content=" + content
				+ ", registerTime=" + registerTime + ", fileInfos=" + fileInfos + "]";
	}

}
