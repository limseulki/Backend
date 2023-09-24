package com.mountain.backend.postReply.repository;

import com.mountain.backend.postReply.entity.PostReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostReplyRepository extends JpaRepository<PostReply, Long> {
    List<PostReply> findAllByPostId(Long id);
}
