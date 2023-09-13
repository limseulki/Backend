package com.mountain.backend.security.jwt;

import java.security.Key;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.mountain.backend.security.auth.UserDetailsServiceImpl;
import com.mountain.backend.security.refreshToken.RefreshToken;
import com.mountain.backend.security.refreshToken.RefreshTokenRepository;

import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

	private static final String BEARER_PREFIX = "Bearer";
	public static final String ACCESS_KEY = "ACCESS_KEY";
	public static final String REFRESH_KEY = "REFRESH_KEY";
	private static final Long ACCESS_TIME = Duration.ofMinutes(60).toMillis();
	private static final Long REFRESH_TIME = Duration.ofDays(7).toMillis();

	@Value("${jwt.secret.key}")
	private String secretKey;
	private Key key;

	private final RefreshTokenRepository refreshTokenRepository;
	private final UserDetailsServiceImpl userDetailsService;

	private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	@PostConstruct
	public void init() {

		byte[] bytes = Base64.getDecoder().decode(secretKey);
		key = Keys.hmacShaKeyFor(bytes);

	}

	public String resolveToken(HttpServletRequest request, String token) {
		String tokenName = token.equals("ACCESS_KEY") ? ACCESS_KEY : REFRESH_KEY;
		String bearerToken = request.getHeader(tokenName);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public TokenDto createAllToken(String userId) {

		log.info("Attempting to create tokens for user: " + userId);
		TokenDto tokenDto = new TokenDto(createToken(userId, "Access"), createToken(userId, "Refresh"));
		log.info("Access Token: " + tokenDto.getAccessToken());
		log.info("Refresh Token: " + tokenDto.getRefreshToken());
		return tokenDto;

	}

	public String createToken(String userId, String token) {

		Date date = new Date();
		long tokenType = token.equals("Access") ? ACCESS_TIME : REFRESH_TIME;

		return
			BEARER_PREFIX +
				Jwts.builder()
					.setSubject(userId)
					.setExpiration(new Date(date.getTime() + tokenType))
					.setIssuedAt(date)
					.signWith(key, signatureAlgorithm)
					.compact();

	}


	//토큰검증
	public boolean validateToken(String token) {

		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);	//파싱빌더 > ket로 서명검증 > jwt유효성검사
			return true;
		} catch (SecurityException | MalformedJwtException e) {						//jwt서명이 올바르지않을때
			log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
		} catch (ExpiredJwtException e) {											//만료
			log.info("Expired JWT token, 만료된 JWT token 입니다.");
		} catch (UnsupportedJwtException e) {										//지원하지않을때
			log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
		} catch (IllegalArgumentException e) {										//claim이 비어있을때
			log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
		}
		return false;

	}

	// 토큰에서 사용자 정보 가져오기
	public String getUserInfoFromToken(String token) {

		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();

	}

	public Authentication createAuthentication(String username) {

		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

	}
	public boolean refreshTokenValid(String token) {
		if (!validateToken(token)) return false;
		Optional<RefreshToken> refreshToken = refreshTokenRepository.findByMemberId(getUserInfoFromToken(token));
		return refreshToken.isPresent() && token.equals(refreshToken.get().getRefreshToken().substring(7));
	}

	public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
		response.setHeader(ACCESS_KEY, accessToken);
	}

	public long getExpirationTime(String token) {

		// 토큰에서 만료 시간 정보를 추출
		Claims claims = Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token)
			.getBody();

		// 현재 시간과 만료 시간의 차이를 계산하여 반환
		Date expirationDate = claims.getExpiration();
		Date now = new Date();
		return (expirationDate.getTime() - now.getTime()) / 1000;

	}



}
