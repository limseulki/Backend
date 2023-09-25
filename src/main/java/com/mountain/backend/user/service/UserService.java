package com.mountain.backend.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mountain.backend.common.Message.Message;
import com.mountain.backend.common.Message.StatusEnum;
import com.mountain.backend.post.entity.Post;
import com.mountain.backend.post.repository.PostRepository;
import com.mountain.backend.security.jwt.JwtUtil;
import com.mountain.backend.security.jwt.TokenDto;
import com.mountain.backend.user.dto.ResponseDto.UserInfoResponseDto;
import com.mountain.backend.user.dto.request.KakaoUserInfoDto;
import com.mountain.backend.user.dto.response.LoginResponseDto;
import com.mountain.backend.user.dto.response.UserPostsResponseDto;
import com.mountain.backend.user.entity.User;
import com.mountain.backend.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final PostRepository postRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;


	@Value("${kakao.client.id}")
	private String KAKAO_CLIENT_ID;

	@Value("${kakao.client.secret}")
	private String KAKAO_CLIENT_SECRET;

	@Value("${kakao.redirect.url}")
	private String KAKAO_REDIRECT_URL;


	public ResponseEntity<Message> login(String code, HttpServletResponse response) throws
		JsonProcessingException {
		log.info("code : " + code);

		String accessToken = getToken(code);

		KakaoUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);

		User kakaoUser = registerKakaoUserIfNeeded(kakaoUserInfo);

		createToken(kakaoUser,response);

		LoginResponseDto loginResponseDto = new LoginResponseDto(kakaoUser);
		log.info(loginResponseDto.getAccessToken());
		log.info(loginResponseDto.getRefreshToken());


		return ResponseEntity.ok(Message.setSuccess(StatusEnum.OK, "로그인 성공", loginResponseDto));


	}
	// 1. "인가 코드"로 "액세스 토큰" 요청
	private String getToken(String code) throws JsonProcessingException {
		// HTTP Header 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HTTP Body 생성
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", "authorization_code");
		body.add("client_id", KAKAO_CLIENT_ID);
		body.add("redirect_uri", KAKAO_REDIRECT_URL);
		body.add("code", code);

		// HTTP 요청 보내기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
			new HttpEntity<>(body, headers);
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> response = rt.exchange(
			"https://kauth.kakao.com/oauth/token",
			HttpMethod.POST,
			kakaoTokenRequest,
			String.class
		);

		// HTTP 응답 (JSON) -> 액세스 토큰 파싱
		String responseBody = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(responseBody);
		return jsonNode.get("access_token").asText();
	}
	// 2. 토큰으로 카카오 API 호출 : "액세스 토큰"으로 "카카오 사용자 정보" 가져오기
	private KakaoUserInfoDto getKakaoUserInfo(String accessToken) throws JsonProcessingException {
		log.info("accessToken : " + accessToken);
		// HTTP Header 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HTTP 요청 보내기
		HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> response = rt.exchange(
			"https://kapi.kakao.com/v2/user/me",
			HttpMethod.POST,
			kakaoUserInfoRequest,
			String.class
		);

		String responseBody = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(responseBody);
		Long id = jsonNode.get("id").asLong();
		String nickname = jsonNode.get("properties")
			.get("nickname").asText();
		String email = jsonNode.get("kakao_account")
			.get("email").asText();
		String gender = jsonNode.get("kakao_account")
			.get("gender").asText();
		String age_range = jsonNode.get("kakao_account")
			.get("age_range").asText();

		log.info("카카오 사용자 아이디 : " + id);
		log.info("카카오 사용자 닉네임 : " + nickname);
		log.info("카카오 사용자 이메일 : " + email);
		log.info("카카오 사용자 성별 : " + gender);
		log.info("카카오 사용자 연령대 : " + age_range);
		return new KakaoUserInfoDto(id, email,nickname,gender,age_range);
	}
	// 3. 필요시에 회원가입
	@Transactional
	public User registerKakaoUserIfNeeded(KakaoUserInfoDto kakaoUserInfo) {
		// DB 에 중복된 Kakao Id 가 있는지 확인
		Long kakaoId = kakaoUserInfo.getId();
		User kakaoUser = userRepository.findByKakaoId(kakaoId)
			.orElse(null);
		if (kakaoUser == null) {
			// 카카오 사용자 email 동일한 email 가진 회원이 있는지 확인
			String kakaoEmail = kakaoUserInfo.getEmail();
			kakaoUser = userRepository.findByEmail(kakaoEmail).orElse(null);
			if (kakaoUser != null) {
				// 기존 회원정보에 카카오 Id 추가
				kakaoUser = kakaoUser.kakaoIdUpdate(kakaoId);
			} else {
				// 신규 회원가입
				// password: random UUID
				String password = UUID.randomUUID().toString();
				String encodedPassword = passwordEncoder.encode(password);

				kakaoUser =  User.builder()
					.kakaoId(kakaoId)
					.email(kakaoEmail)
					.nickname(kakaoUserInfo.getNickname())
					.password(encodedPassword)
					.ageRange(kakaoUserInfo.getAgeRange())
					.gender(kakaoUserInfo.getGender())
					.build();
				userRepository.save(kakaoUser);

			}
		}
		return kakaoUser;
	}

	private void createToken(User user, HttpServletResponse response) {

		String userId = user.getEmail();
		TokenDto tokenDto = jwtUtil.createAllToken(userId);

		response.setHeader("Access", tokenDto.getAccessToken());
		response.setHeader("Refresh", tokenDto.getRefreshToken());

	}

	// 마이페이지 - 내 정보 조회
	public ResponseEntity<Message> userInfo(Long id) {

		User user = userRepository.findById(id).orElseThrow();

		UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto(user);

		Message message = Message.setSuccess(StatusEnum.OK, "내 정보 조회 성공", userInfoResponseDto);
		return new ResponseEntity<>(message, HttpStatus.OK);

	}

	// 마이페이지 - 후기 모아보기
	public ResponseEntity<Message> userPosts(Long id) {

		User user = userRepository.findById(id).orElseThrow();
		List<Post> postList = postRepository.findAllByUserId(id);
		List<UserPostsResponseDto> userPostsResponseDtoList = new ArrayList<>();

		for(Post post : postList) {
			userPostsResponseDtoList.add(new UserPostsResponseDto(post, user));
		}

		Message message = Message.setSuccess(StatusEnum.OK, "내 후기 모아보기 성공", userPostsResponseDtoList);
		return new ResponseEntity<>(message, HttpStatus.OK);

	}
}
