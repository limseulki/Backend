package com.mountain.backend.post.dto.ResponseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mountain.backend.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private final Long id;

    private final String tags;

    private final String content;

    private final boolean heart;

    private final String imageUrlList;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.tags = post.getTags();
        this.content = post.getContent();
        this.heart = post.isHeart();
        this.imageUrlList = post.getImageUrlList();
        this.createdAt = post.getCreatedAt();
    }

}
