package com.ssafy.member.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class MemberDto {

	@Schema(description = "회원아이디", requiredMode = Schema.RequiredMode.REQUIRED)
	private String userId;
	@Schema(description = "회원이름")
	private String userName;
	@Schema(description = "회원비밀번호")
	private String userPwd;
	@Schema(description = "이메일아이디")
	private String emailId;
	@Schema(description = "이메일도메인")
	private String emailDomain;
	@Schema(description = "가입일", defaultValue = "현재시간")
	private String joinDate;
	@Schema(description = "탈퇴상태")
	private int status;
	@Schema(description = "refreshToken", defaultValue = "")
	private String refreshToken;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Override
	public String toString() {
		return "MemberDto [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", emailId="
				+ emailId + ", emailDomain=" + emailDomain + ", joinDate=" + joinDate + ", status=" + status
				+ ", refreshToken=" + refreshToken + "]";
	}

	
}
