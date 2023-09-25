package com.mountain.backend.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mountain.backend.post.entity.Post;
import com.mountain.backend.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserPostsResponseDto {

    private final Long id;

    private final String nickname;

    private final String tags;

    private final String content;

    private final boolean heart;

    private final String imageUrlList;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public UserPostsResponseDto(Post post, User user) {
        this.id = post.getId();
        this.nickname = user.getNickname();
        this.tags = post.getTags();
        this.content = post.getContent();
        this.heart = post.isHeart();
        this.imageUrlList = post.getImageUrlList();
        this.createdAt = post.getCreatedAt();
    }

}
