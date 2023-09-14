package com.mountain.backend.post.repository;

import com.mountain.backend.post.entity.Post;
import com.mountain.backend.postReply.entity.PostReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByMountainId(Long id);

}
