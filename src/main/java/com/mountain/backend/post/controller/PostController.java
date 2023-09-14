package com.mountain.backend.post.controller;

import com.mountain.backend.common.util.Message;
import com.mountain.backend.post.dto.RequestDto.PostRequestDto;
import com.mountain.backend.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 산 리뷰 목록 조회(리뷰버튼 클릭)
    @GetMapping(value = "api/mountain/{mountainId}/posts")
    public ResponseEntity<Message> readPost(@PathVariable("mountainId") Long id) {
        return postService.readPost(id);
    }

    // 산 리뷰 작성
    @PostMapping(value = "api/post")
    public ResponseEntity<Message> createPost(@ModelAttribute PostRequestDto requestDto) throws IOException {
        return postService.createPost(requestDto);
    }

    // 산 리뷰 수정
    @PutMapping(value = "api/post/{postId}")
    public ResponseEntity<Message> modifyPost(@PathVariable("postId") Long id, @ModelAttribute PostRequestDto requestDto) {
        return postService.modifyPost(id, requestDto);
    }

    // 산 리뷰 삭제
    @DeleteMapping(value = "api/post/{postId}")
    public ResponseEntity<Message> deletePost(@PathVariable("postId") Long id) {
        return postService.deletePost(id);
    }

}
