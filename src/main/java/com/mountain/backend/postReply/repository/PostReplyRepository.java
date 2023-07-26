package com.mountain.backend.postReply.repository;

import com.mountain.backend.postReply.entity.PostReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReplyRepository extends JpaRepository<PostReply, Long> {
}
