package com.mountain.backend.mountain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mountain.backend.common.Message.Message;
import com.mountain.backend.mountain.dto.SearchRequestDto;
import com.mountain.backend.mountain.service.MountainService;
import com.mountain.backend.mountain.service.TestService;

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
	@GetMapping()
	public ResponseEntity<Message> hikingStart(){

		return null;
	}
	//등산일시정지
	@GetMapping()
	public ResponseEntity<Message> hikingPause(){

		return null;
	}
	//등산종료
	@GetMapping()
	public ResponseEntity<Message> hikingEnd(){

		return null;
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
