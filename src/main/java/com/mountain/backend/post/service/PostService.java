package com.mountain.backend.post.service;

import com.mountain.backend.common.util.Message;
import com.mountain.backend.common.util.S3Uploader;
import com.mountain.backend.common.util.StatusEnum;
import com.mountain.backend.mountain.repository.MountainRepository;
import com.mountain.backend.post.dto.RequestDto.PostRequestDto;
import com.mountain.backend.post.dto.ResponseDto.PostResponseDto;
import com.mountain.backend.post.entity.Post;
import com.mountain.backend.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MountainRepository mountainRepository;
    private final S3Uploader s3Uploader;

    // 산 리뷰 목록 조회(리뷰버튼 클릭)
    @Transactional(readOnly = true)
    public ResponseEntity<Message> readPost(Long mountainId) {

        List<Post> postList = postRepository.findAllByMountainId(mountainId);
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        for(Post post : postList) {
            postResponseDtoList.add(new PostResponseDto(post));
        }

        Message message = Message.setSuccess(StatusEnum.OK,"산 리뷰 목록 조회 성공", postResponseDtoList);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    // 산 리뷰 작성
    @Transactional
    public ResponseEntity<Message> createPost(Long mountainId, PostRequestDto requestDto) throws IOException {

        StringBuilder imageUrls = new StringBuilder();

        for (MultipartFile image : requestDto.getImages()) {
            String imageUrl = s3Uploader.upload(image);
            imageUrls.append(imageUrl + " ");
        }

        Post post = Post.builder()
                .tags(requestDto.getTags())
                .content(requestDto.getContent())
                .imageUrlList(imageUrls.toString())
                .mountain(mountainRepository.findById(mountainId).orElseThrow())
                .build();

        postRepository.save(post);

        Message message = Message.setSuccess(StatusEnum.OK, "산 리뷰 작성 성공", post.getId());
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    // 산 리뷰 수정
    @Transactional
    public ResponseEntity<Message> modifyPost(Long id, PostRequestDto requestDto) throws IOException {

        Post post = postRepository.findById(id).orElseThrow();  // 예외처리 추가하기

        StringBuilder imageUrls = new StringBuilder();

        for (MultipartFile image : requestDto.getImages()) {
            String imageUrl = s3Uploader.upload(image);
            imageUrls.append(imageUrl + " ");
        }

        post.update(requestDto, imageUrls.toString());

        Message message = Message.setSuccess(StatusEnum.OK,"산 리뷰 수정 성공", post.getId());
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    // 산 리뷰 삭제
    @Transactional
    public ResponseEntity<Message> deletePost(Long id) {

        Post post = postRepository.findById(id).orElseThrow();  // 예외처리 추가하기

        post.delete();

        Message message = Message.setSuccess(StatusEnum.OK,"산 리뷰 삭제 성공", post.getId());
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

}
