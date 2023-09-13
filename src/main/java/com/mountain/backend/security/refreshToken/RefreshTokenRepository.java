package com.mountain.backend.security.refreshToken;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository  extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByMemberId(String userId);

	void deleteByMemberId(String userId);

}