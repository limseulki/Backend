package com.mountain.backend.postReply.service;

import com.mountain.backend.common.util.Message;
import com.mountain.backend.common.util.StatusEnum;
import com.mountain.backend.post.repository.PostRepository;
import com.mountain.backend.postReply.dto.RequestDto.PostReplyRequestDto;
import com.mountain.backend.postReply.dto.ResponseDto.PostReplyResponseDto;
import com.mountain.backend.postReply.entity.PostReply;
import com.mountain.backend.postReply.repository.PostReplyRepository;
import com.mountain.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReplyService {

    private final PostRepository postRepository;
    private final PostReplyRepository postReplyRepository;
    private final UserRepository userRepository;

    // 댓글 목록 조회
    @Transactional(readOnly = true)
    public ResponseEntity<Message> readPostReply(Long postId) {

        List<PostReply> postReplyList = postReplyRepository.findAllByPostId(postId);
        List<PostReplyResponseDto> postReplyResponseDtoList = new ArrayList<>();

        for(PostReply postReply : postReplyList) {
            postReplyResponseDtoList.add(new PostReplyResponseDto(postReply));
        }

        Message message = Message.setSuccess(StatusEnum.OK, "댓글 목록 조회 성공", postReplyResponseDtoList);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    // 댓글 작성
    @Transactional
    public ResponseEntity<Message> createPostReply(Long postId, PostReplyRequestDto requestDto) {

        PostReply postReply = PostReply.builder()
                .comment(requestDto.getComment())
                .post(postRepository.findById(postId).orElseThrow())
//                .user(userRepository.findById(requestDto.getUserId()).orElseThrow())
                .build();

        postReplyRepository.save(postReply);

        Message message = Message.setSuccess(StatusEnum.OK, "댓글 작성 성공", postReply.getId());
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

}
