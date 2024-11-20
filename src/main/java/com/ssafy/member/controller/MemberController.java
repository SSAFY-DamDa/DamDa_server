package com.ssafy.member.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.util.AesUtil;
import com.ssafy.util.JWTUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Tag(name = "회원 인증 컨트롤러", description = "로그인, 로그아웃, 회원 가입 및 기타 회원 관련 작업을 처리하는 클래스입니다.")
public class MemberController {

	private final Logger log = LoggerFactory.getLogger(MemberController.class);
	private final MemberService memberService;
	private final JWTUtil jwtUtil;

	public MemberController(MemberService memberService, JWTUtil jwtUtil) {
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
	}

	@Operation(summary = "아이디 중복 체크", description = "회원 가입 시 아이디의 중복 여부를 확인합니다.")
	@GetMapping("/idcheck")
	public ResponseEntity<Map<String, String>> idCheck(@RequestParam String checkid) {
		Map<String, String> resultMap = new HashMap<>();
		try {
			int cnt = memberService.idCheck(checkid);
			resultMap.put("checkid", checkid);
			resultMap.put("cnt", String.valueOf(cnt));
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			log.error("아이디 중복 체크 중 에러 발생: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "회원 가입", description = "회원 정보를 받아 회원 가입을 처리합니다.")
	@PostMapping("/join")
	public ResponseEntity<String> join(@RequestBody MemberDto memberDto) {
		try {
			String encryptPWD = AesUtil.encrypt(memberDto.getUserPwd());
			memberDto.setUserPwd(encryptPWD);
			memberService.joinMember(memberDto);
			return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("회원 가입 중 에러 발생: {}", e.getMessage());
			return new ResponseEntity<>("회원가입 중 에러 발생", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "로그인", description = "아이디와 비밀번호를 이용하여 로그인 처리.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		System.out.println("dto: ............." + memberDto);
		try {
			String encryptPWD = AesUtil.encrypt(memberDto.getUserPwd());
			memberDto.setUserPwd(encryptPWD);
			MemberDto loginUser = memberService.loginMember(memberDto.getUserId(), encryptPWD);

			if (loginUser != null && loginUser.getStatus() == 1) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());

				Map<String, String> tokenMap = new HashMap<>();
				tokenMap.put("userId", loginUser.getUserId());
				tokenMap.put("token", refreshToken);
				memberService.saveRefreshToken(tokenMap);

				resultMap.put("userName", loginUser.getUserName());
				resultMap.put("accessToken", accessToken);
				resultMap.put("refreshToken", refreshToken);

				loginUser.setRefreshToken(refreshToken);

				HttpSession session = request.getSession();
				session.setAttribute("userinfo", loginUser);

				String idsave = memberDto.getUserId();
				if (idsave != null) { // 아이디 저장을 체크 했다면.
					Cookie cookie = new Cookie("ssafy_id", memberDto.getUserId());
					cookie.setPath("/");
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40); // 40년간 저장.
					response.addCookie(cookie);
				}
				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 비밀번호 확인 후 다시 로그인하세요.");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			log.error("로그인 중 에러 발생: {}", e.getMessage());
			resultMap.put("message", "로그인 중 에러 발생");
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@Operation(summary = "로그아웃", description = "로그아웃 처리.")
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) throws SQLException {
		HttpSession session = request.getSession();

		// 세션에서 필요한 정보를 먼저 가져와 로컬 변수에 저장
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null) {
			Map<String, String> tokenMap = new HashMap<>();
			tokenMap.put("userId", memberDto.getUserId());
			tokenMap.put("token", memberDto.getRefreshToken());

			// RefreshToken 삭제 처리
			memberService.deleteRefreshToken(tokenMap);
		}

		// 세션 무효화
		session.invalidate();

		return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
	}

	@Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
	@PutMapping("/modify")
	public ResponseEntity<String> modifyInfo(@RequestHeader("Authorization") String authorization,
			@RequestHeader HttpHeaders header, @RequestBody MemberDto memberDto) {
		if (jwtUtil.checkToken(authorization)) {
			try {
				String encryptPwd = AesUtil.encrypt(memberDto.getUserPwd());
				memberDto.setUserPwd(encryptPwd);
				memberService.modifyInfo(memberDto);

				return new ResponseEntity<>("회원 정보 수정 성공", HttpStatus.OK);
			} catch (Exception e) {
				log.error("회원 정보 수정 중 에러 발생: {}", e.getMessage());
				return new ResponseEntity<>("회원 정보 수정 중 에러 발생", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			log.error("사용 불가능한 토큰");
			return new ResponseEntity<>("토큰 사용 불가", HttpStatus.UNAUTHORIZED);
		}

	}

	@Operation(summary = "회원 탈퇴", description = "회원 탈퇴 처리.")
	@DeleteMapping("/delete")
	public ResponseEntity<String> withdrawUser(@RequestBody MemberDto memberDto) {
		try {
			String encryptPwd = AesUtil.encrypt(memberDto.getUserPwd());
			memberDto.setUserPwd(encryptPwd);
			memberService.withdrawUser(memberDto);
			return new ResponseEntity<>("회원 탈퇴 성공", HttpStatus.OK);
		} catch (Exception e) {
			log.error("회원 탈퇴 중 에러 발생: {}", e.getMessage());
			return new ResponseEntity<>("회원 탈퇴 중 에러 발생", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "비밀번호 찾기", description = "회원 아이디를 통해 비밀번호를 찾습니다.")
	@PostMapping("/findpwd")
	public ResponseEntity<Map<String, String>> findPassword(@RequestBody MemberDto memberDto) {
		Map<String, String> resultMap = new HashMap<>();
		try {
			String password = memberService.findPWD(memberDto);
			String decryptPWD = AesUtil.decrypt(password);
			if(decryptPWD == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			resultMap.put("password", decryptPWD);
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(memberDto);
			log.error("비밀번호 찾기 중 에러 발생: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "비밀번호 재설정", description = "회원의 새 비밀번호를 등록합니다.")
	@PostMapping("/resetpwd")
	public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> map) {
	    try {
	        String encryptPWD = AesUtil.encrypt(map.get("newPwd"));
	        map.replace("newPwd", encryptPWD);
	        int resetPWD = memberService.resetPWD(map);
	        System.out.println(resetPWD);
	        return new ResponseEntity<>("비밀번호 재설정 성공", HttpStatus.OK);
	    } catch (Exception e) {
	        log.error("비밀번호 재설정 중 에러 발생: {}", e.getMessage());
	        return new ResponseEntity<>("비밀번호 재설정 중 에러 발생", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	@Operation(summary = "회원 조회", description = "회원의 정보를 조회합니다.")
	@GetMapping("/findbyid/{id}")
	public ResponseEntity<MemberDto> getUserInfo(@PathVariable String id) {
		try {
			MemberDto member = memberService.selectUser(id);

			String decryptPWD = AesUtil.decrypt(member.getUserPwd());
			member.setUserPwd(decryptPWD);

			return new ResponseEntity<>(member, HttpStatus.OK);
		} catch (Exception e) {
			log.error("사용자 정보 불러오는 중 오류 발생: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Access Token 재발급", description = "만료된 access token 을 재발급 받는다.")
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, @RequestHeader("refreshToken") String token)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		log.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 access token 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("refresh token 도 사용 불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
