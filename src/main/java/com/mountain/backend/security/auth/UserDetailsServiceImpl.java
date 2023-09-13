package com.mountain.backend.security.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mountain.backend.user.entity.User;
import com.mountain.backend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(userId)
			.orElseThrow( ()-> new UsernameNotFoundException("사용자를 찾을 수 없습니다"));


		return new UserDetailsImpl(user,user.getEmail());
	}
}
