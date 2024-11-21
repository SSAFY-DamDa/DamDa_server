package com.ssafy.member.model.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public int idCheck(String userId) throws SQLException {
		return memberMapper.idCheck(userId);
	}

	@Override
	public int joinMember(MemberDto memberDto) throws SQLException {
		return memberMapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws SQLException {
		return memberMapper.loginMember(userId, userPwd);
	}

	@Override
	public void modifyInfo(MemberDto memberDto) throws SQLException {
		memberMapper.modifyInfo(memberDto);
	}

	@Override
	public MemberDto viewInfo(MemberDto memberDto) throws SQLException {
		return memberMapper.viewInfo(memberDto);
	}

	@Override
	public String findPWD(MemberDto memberDto) throws SQLException {
		return memberMapper.findPWD(memberDto);
	}

	public int resetPWD(Map<String, String> map) throws SQLException {
		return memberMapper.resetPWD(map);
	}

	@Override
	public void withdrawUser(MemberDto memberDto) throws SQLException {
		memberMapper.withdrawUser(memberDto);
	}

	@Override
	public MemberDto selectUser(String userId) throws SQLException {
		return memberMapper.selectUser(userId);
	}

	// JWT related methods
	@Override
	public void saveRefreshToken(Map<String, String> map) throws SQLException {
		memberMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws SQLException {
		return memberMapper.getRefreshToken(userId);
	}

	@Override
	public void deleteRefreshToken(Map<String, String> map) throws SQLException {
		memberMapper.deleteRefreshToken(map);
	}
}
