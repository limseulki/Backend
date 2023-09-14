package com.mountain.backend.mountain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mountain.backend.common.Message.Message;
import com.mountain.backend.mountain.dto.ResponseDto;
import com.mountain.backend.mountain.entity.Mountain;
import com.mountain.backend.mountain.repository.MountainRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MountainService {

	private final MountainRepository mountainRepository;

	public ResponseEntity<Message> findAll(){

		List<Mountain> mountains = mountainRepository.findAll();

		return null;

	}


}
