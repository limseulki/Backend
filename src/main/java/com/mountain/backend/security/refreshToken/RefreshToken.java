package com.mountain.backend.security.refreshToken;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class RefreshToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private String refreshToken;

	private String memberId;

	public RefreshToken(String tokenDto,  String memberId) {
		this.refreshToken = tokenDto;
		this.memberId = memberId;
	}

	public RefreshToken updateToken(String tokenDto) {
		this.refreshToken = tokenDto;
		return this;
	}

}