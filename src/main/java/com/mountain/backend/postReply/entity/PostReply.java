package com.mountain.backend.postReply.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.Name;

@Entity
public class PostReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("postReplyId")
    private Long id;

}
