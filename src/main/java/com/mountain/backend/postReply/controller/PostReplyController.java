package com.mountain.backend.postReply.controller;

import com.mountain.backend.common.util.Message;
import com.mountain.backend.postReply.dto.RequestDto.PostReplyRequestDto;
import com.mountain.backend.postReply.service.PostReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostReplyController {

    private final PostReplyService postReplyService;

    // 댓글 목록 조회
    @GetMapping(value = "api/post/{postId}/postReply")
    public ResponseEntity<Message> readPostReply(@PathVariable("postId") Long id) {
        return postReplyService.readPostReply(id);
    }

    // 댓글 작성
    @PostMapping(value = "api/post/{postId}/postReply")
    public ResponseEntity<Message> createPostReply(@PathVariable("postId") Long id, @ModelAttribute PostReplyRequestDto requestDto) {
        return postReplyService.createPostReply(id, requestDto);
    }
}
