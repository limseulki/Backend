package com.mountain.backend.meet.controller;

import com.mountain.backend.common.util.Message;
import com.mountain.backend.meet.dto.RequestDto.MeetRequestDto;
import com.mountain.backend.meet.service.MeetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class MeetController {

    private final MeetService meetService;

    // 모임 만들기
    @PostMapping(value = "api/meet/create")
    public ResponseEntity<Message> createMeet(@ModelAttribute MeetRequestDto requestDto) throws IOException {
        return meetService.createMeet(requestDto);
    }

    // 모임 수정
    @PutMapping(value = "api/meet/{meetId}")
    public ResponseEntity<Message> modifiyMeet(@PathVariable("meetId") Long id, @ModelAttribute MeetRequestDto requestDto) {
        return meetService.modifyMeet(id, requestDto);
    }

    // 모임 삭제
    @DeleteMapping(value = "api/meet/{meetId}")
    public ResponseEntity<Message> deleteMeet(@PathVariable("meetId") Long id) {
        return meetService.deleteMeet(id);
    }

}
