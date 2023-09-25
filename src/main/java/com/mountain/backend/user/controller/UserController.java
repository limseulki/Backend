package com.mountain.backend.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mountain.backend.common.Message.Message;
import com.mountain.backend.user.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	private final UserService userService;

	@GetMapping("/kakao/callback")
	public ResponseEntity<Message> login(@RequestParam String code, HttpServletResponse response) throws
		JsonProcessingException {

		return userService.login(code,response);

	}

	// 마이페이지 - 내 정보 조회
	@GetMapping(value = "{userId}")
	public ResponseEntity<Message> userInfo(@PathVariable("userId") Long id) {
		return userService.userInfo(id);
	}

	// 마이페이지 - 후기 모아보기
	@GetMapping(value = "{userId}/posts")
	public ResponseEntity<Message> userPosts(@PathVariable("userId") Long id) {
		return userService.userPosts(id);
	}

}
