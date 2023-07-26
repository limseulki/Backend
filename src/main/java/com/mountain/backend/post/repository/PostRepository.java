package com.mountain.backend.post.repository;

import com.mountain.backend.postReply.entity.PostReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostReply, Long> {
}
