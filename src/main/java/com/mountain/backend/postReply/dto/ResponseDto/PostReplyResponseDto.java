package com.mountain.backend.postReply.dto.ResponseDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mountain.backend.postReply.entity.PostReply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostReplyResponseDto {

    private final Long id;

    private final String nickname;

    private final String comment;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private final boolean heart;

    public PostReplyResponseDto(PostReply postReply) {
        this.id = postReply.getId();
        this.nickname = postReply.getUser().getNickname();
        this.comment = postReply.getComment();
        this.createdAt = postReply.getCreatedAt();
        this.heart = postReply.isHeart();
    }
}
