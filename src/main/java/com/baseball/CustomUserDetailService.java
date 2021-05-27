package com.baseball;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.baseball.repo.UserJpaRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
	private final UserJpaRepo userJpaRepo;
	
	public UserDetails loadUserByUsername(String userpk) {
		return userJpaRepo.findById(userpk).orElseThrow(CUserNotFoundException::new);
	}
}
