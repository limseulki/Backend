package com.mountain.backend.user.dto.ResponseDto;

import com.mountain.backend.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoResponseDto {

    private String nickname;

    private String ageRange;

    private String gender;

    private int complete_climb_cnt;

    private int complete_climb_time;

    public UserInfoResponseDto(User user) {
        this.nickname = user.getNickname();
        this.ageRange = user.getAgeRange();
        this.gender = user.getGender();
//        this.complete_climb_cnt = user.getComplete_climb_cnt;
//        this.complete_climb_time = user.getComplete_climb_time;
    }

}
