package com.mountain.backend.meet.service;

import com.mountain.backend.common.util.Message;
import com.mountain.backend.common.util.S3Uploader;
import com.mountain.backend.common.util.StatusEnum;
import com.mountain.backend.meet.dto.RequestDto.MeetRequestDto;
import com.mountain.backend.meet.entity.Meet;
import com.mountain.backend.meet.repository.MeetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MeetService {

    private final MeetRepository meetRepository;
    private final S3Uploader s3Uploader;

    // 모임 만들기
    @Transactional
    public ResponseEntity<Message> createMeet(MeetRequestDto requestDto) throws IOException {

        String imageUrl = s3Uploader.upload(requestDto.getImgUrl());

        Meet meet = Meet.builder()
                .title(requestDto.getTitle())
                .departureDate(requestDto.getDepartureDate())
                .meetSize(requestDto.getMeetSize())
                .age(requestDto.getAge())
                .gender(requestDto.getGender())
                .tag(requestDto.getTag())
                .location(requestDto.getLocation())
                .course(requestDto.getCourse())
                .content(requestDto.getContent())
                .imgUrl(imageUrl)
                .openDate(requestDto.getOpenDate())
                .closingDate(requestDto.getClosingDate())
                .isDeleted(false)
                .build();

        meetRepository.save(meet);

        Message message = Message.setSuccess(StatusEnum.OK,"모임 만들기 성공", meet.getId());
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    // 모임 수정
    @Transactional
    public ResponseEntity<Message> modifyMeet(Long id, MeetRequestDto requestDto) {

        Meet meet = meetRepository.findById(id).orElseThrow();  // 예외처리 추가하기

        meet.update(requestDto);

        Message message = Message.setSuccess(StatusEnum.OK,"모임 수정 성공", meet.getId());
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    // 모임 삭제
    @Transactional
    public ResponseEntity<Message> deleteMeet(Long id) {

        Meet meet = meetRepository.findById(id).orElseThrow();  // 예외처리 추가하기

        meet.delete();

        Message message = Message.setSuccess(StatusEnum.OK,"모임 삭제 성공", meet.getId());
        return new ResponseEntity<>(message, HttpStatus.OK);

    }
}
