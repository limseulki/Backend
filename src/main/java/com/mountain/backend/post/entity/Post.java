package com.mountain.backend.post.entity;

import com.mountain.backend.common.util.Timestamped;
import com.mountain.backend.mountain.entity.Mountain;
import com.mountain.backend.post.dto.RequestDto.PostRequestDto;
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
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("postId")
    private Long id;            // 게시글(산리뷰)아이디

    @Column
    private String tags;        // 태그

    @Column
    private String content;     // 내용

    @Column
    private boolean heart;       // 좋아요

    @Column
    private String imageUrlList;    // 이미지

    @ManyToOne(fetch = FetchType.LAZY)
    private Mountain mountain;

    private boolean isDelete;

    public void update(PostRequestDto requestDto, String imageUrls) {
        this.tags = requestDto.getTags();
        this.content = requestDto.getContent();
        this.imageUrlList = imageUrls;
    }

    public void delete() {
        this.isDelete = true;
    }
}
