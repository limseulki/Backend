package com.mountain.backend.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoDto {

	private Long id;
	private String email;
	private String nickname;
	private String ageRange;
	private String gender;

	public KakaoUserInfoDto(Long id, String email, String nickname,String gender, String ageRange){
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.ageRange = ageRange;
		this.gender = gender;
	}

}
