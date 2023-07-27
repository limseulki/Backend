package com.mountain.backend.mountain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mountain.backend.mountain.dto.SearchRequestDto;
import com.mountain.backend.mountain.entity.Message;
import com.mountain.backend.mountain.service.TestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MountainController {

	private final TestService testService;

	@PostMapping("/api/test")
	public ResponseEntity<Message> test(@RequestBody SearchRequestDto searchRequestDto){

		return testService.test(searchRequestDto);
	}
}
