package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.member.model.MemberDto;

public interface MemberService {
	int idCheck(String userId) throws Exception;

	int joinMember(MemberDto memberDto) throws Exception;

	MemberDto loginMember(String userId, String userPwd) throws Exception;

	public void modifyInfo(MemberDto memberDto) throws Exception;

	public MemberDto viewInfo(MemberDto memberDto) throws Exception;

	public String findPWD(MemberDto memberDto) throws Exception;
	
	public int resetPWD(Map<String, String> map) throws SQLException;

	public void withdrawUser(MemberDto memberDto) throws Exception;

	public MemberDto selectUser(String userId) throws Exception;

	void saveRefreshToken(Map<String, String> map) throws SQLException;

	Object getRefreshToken(String userid) throws SQLException;

	void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
