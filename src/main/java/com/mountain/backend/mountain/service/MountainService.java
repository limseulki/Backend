package com.mountain.backend.mountain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.mountain.backend.common.Message.Message;
import com.mountain.backend.common.Message.StatusEnum;
import com.mountain.backend.mountain.entity.Mountain;
import com.mountain.backend.mountain.repository.MountainRepository;
import com.mountain.backend.user.entity.User;
import com.mountain.backend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MountainService {

	private final MountainRepository mountainRepository;
	private final UserRepository userRepository;

	public ResponseEntity<Message> findAll(){

		List<Mountain> mountains = mountainRepository.findAll();

		return null;

	}

	public ResponseEntity<Message> hikingStart(Long userId) {
		User updatedUser = updateUserDateField(userId, new Date(), "start");
		if (updatedUser != null) {
			return ResponseEntity.ok(Message.setSuccess(StatusEnum.OK, "등산 시작!"));
		} else {
			return ResponseEntity.ok(Message.setSuccess(StatusEnum.OK, "찾을수 없는 유저입니다"));
		}
	}

	public ResponseEntity<Message> hikingPause(Long userId) {
		User updatedUser = updateUserDateField(userId, new Date(), "pause");
		if (updatedUser != null) {
			return ResponseEntity.ok(Message.setSuccess(StatusEnum.OK, "등산 일시정지!"));
		} else {
			return ResponseEntity.ok(Message.setSuccess(StatusEnum.OK, "찾을수 없는 유저입니다"));
		}
	}

	public ResponseEntity<Message> hikingEnd(Long userId) {
		User updatedUser = updateUserDateField(userId, new Date(), "end");
		if (updatedUser != null) {
			return ResponseEntity.ok(Message.setSuccess(StatusEnum.OK, "등산 종료!"));
		} else {
			return ResponseEntity.ok(Message.setSuccess(StatusEnum.OK, "찾을수 없는 유저입니다"));
		}
	}

	private User updateUserDateField(Long userId, Date date, String field) {
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			switch (field) {
				case "start" -> user.setHikingStartAt(date);
				case "pause" -> user.setHikingPauseAt(date);
				case "end" -> user.setHikingEndAt(date);
			}
			userRepository.save(user);
			return user;
		}
		return null;
	}


}
