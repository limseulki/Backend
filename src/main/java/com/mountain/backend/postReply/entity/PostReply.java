package com.mountain.backend.postReply.entity;

import com.mountain.backend.common.util.Timestamped;
import com.mountain.backend.post.entity.Post;
import com.mountain.backend.user.entity.User;
import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostReply extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("postReplyId")
    private Long id;            // 댓글아이디

    @Column(nullable = false)
    private String comment;     // 댓글내용

    @Column
    private boolean heart;      // 좋아요

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

}
