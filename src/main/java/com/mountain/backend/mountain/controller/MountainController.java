package com.mountain.backend.mountain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mountain.backend.common.Message.Message;
import com.mountain.backend.mountain.dto.SearchRequestDto;
import com.mountain.backend.mountain.service.MountainService;
import com.mountain.backend.mountain.service.TestService;
import com.mountain.backend.security.auth.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MountainController {

	private final TestService testService;
	private final MountainService mountainService;

	//모든 등산조회
	@GetMapping("/api/main/mountains")
	public ResponseEntity<Message> findAll(){

		return mountainService.findAll();
	}
	//등산코스조회
	@GetMapping()
	public ResponseEntity<Message> findAllMountainCourse(){

		return null;
	}
	//등산시작
	@GetMapping("/api/main/start/{userId}")
	public ResponseEntity<Message> hikingStart(@PathVariable Long userId){



		return mountainService.hikingStart(userId);
	}
	//등산일시정지
	@GetMapping("/api/main/pause/{userId}")
	public ResponseEntity<Message> hikingPause(@PathVariable Long userId){

		return mountainService.hikingPause(userId);
	}
	//등산종료
	@GetMapping("/api/main/end/{userId}")
	public ResponseEntity<Message> hikingEnd(@PathVariable Long userId){

		return mountainService.hikingEnd(userId);
	}


	@PostMapping
	public ResponseEntity<Message> create(){
		return null;

	}
	@DeleteMapping
	public ResponseEntity<Message> delete(){
		return null;
	}
}
