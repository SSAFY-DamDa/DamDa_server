package com.ssafy.member.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;

@Mapper
public interface MemberMapper {

	int idCheck(String userId) throws SQLException;

	int joinMember(MemberDto memberDto) throws SQLException;

	MemberDto loginMember(String userId, String userPwd) throws SQLException;

	public void modifyInfo(MemberDto memberDto) throws SQLException;

	public MemberDto viewInfo(MemberDto memberDto) throws SQLException;

	public String findPWD(MemberDto memberDto) throws SQLException;

	public int resetPWD(Map<String, String> map) throws SQLException;

	public void withdrawUser(MemberDto memberDto) throws SQLException;

	public MemberDto selectUser(String userId) throws SQLException;

//	JWT
	void saveRefreshToken(Map<String, String> map) throws SQLException;

	Object getRefreshToken(String userid) throws SQLException;

	void deleteRefreshToken(Map<String, String> map) throws SQLException;

}
