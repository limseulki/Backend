package com.mountain.backend.meet.service;

import com.mountain.backend.common.util.Message;
import com.mountain.backend.common.util.StatusEnum;
import com.mountain.backend.meet.dto.RequestDto.MeetRequestDto;
import com.mountain.backend.meet.entity.Meet;
import com.mountain.backend.meet.repository.MeetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MeetService {

    private final MeetRepository meetRepository;

    // 모임 만들기
    @Transactional
    public ResponseEntity<Message> createMeet(MeetRequestDto requestDto) {

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
                .openDate(requestDto.getOpenDate())
                .closingDate(requestDto.getClosingDate())
                .build();

        meetRepository.save(meet);

        Message message = Message.setSuccess(StatusEnum.OK,"모임 만들기 성공", meet.getId());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
