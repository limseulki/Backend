package com.mountain.backend.user.dto.response;



import com.mountain.backend.user.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponseDto {

	private Long id;

	private String nickName;

	private String accessToken;

	private String refreshToken;


	public LoginResponseDto(User user) {
		this.id = user.getId();
		this.nickName = user.getNickname();
	}

}
