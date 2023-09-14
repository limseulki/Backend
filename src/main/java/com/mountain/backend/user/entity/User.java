package com.mountain.backend.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Name("userId")
    private Long id;

    private Long kakaoId;

    private String email;

    private String nickname;

    private String password;

    private String ageRange;

    private String gender;


    public User (String email,Long kakaoId, String password, String nickname, String gender, String ageRange){
        this.email= email;
        this.kakaoId = kakaoId;
        this.password = password;
        this.nickname = nickname;
        this.ageRange = ageRange;
        this.gender = gender;
    }

    public User kakaoIdUpdate(Long kakaoId) {

        return null;
    }
}
