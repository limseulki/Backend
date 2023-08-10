package com.mountain.backend.meet.controller;

import com.mountain.backend.common.util.Message;
import com.mountain.backend.meet.dto.RequestDto.MeetRequestDto;
import com.mountain.backend.meet.service.MeetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeetController {

    private final MeetService meetService;

    // 모임 만들기
    @PostMapping(value = "api/meet/create")
    public ResponseEntity<Message> createMeet(@ModelAttribute MeetRequestDto requestDto) {
        return meetService.createMeet(requestDto);
    }

}
