package com.mountain.backend.postReply.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostReplyRequestDto {

    private String comment;     // 댓글내용

//    private Long userId;        // test용 유저아이디

}
