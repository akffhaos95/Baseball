package com.baseball.controller;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baseball.CommonResult;
import com.baseball.ResponseService;
import com.baseball.SingleResult;
import com.baseball.User;
import com.baseball.config.JwtTokenProvider;
import com.baseball.repo.UserJpaRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags= {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value="/sign")
public class SignController {
	private final UserJpaRepo userJpaRepo;
	private final JwtTokenProvider jwtTokenProvider;
	private final ResponseService responseService;
	private final PasswordEncoder passwordEncoder;

	@ApiOperation(value="로그인",notes="이메일 회원 로그인을 한다.")
	@PostMapping(value="/signin")
	public SingleResult<String> signin(
			@ApiParam(value="회원ID", required=true) @RequestParam String id,
			@ApiParam(value="회원PW", required=true) @RequestParam String password) throws Exception {
		User user = userJpaRepo.findById(id).orElseThrow(Exception::new);	
		if (!passwordEncoder.matches(password, user.getPassword()))
			throw new Exception();
		return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getId()), user.getRoles()));
	}
	
	@ApiOperation(value="가입", notes="회원가입을 한다")
	@PostMapping(value="/signup")
	public CommonResult signin(
			@ApiParam(value="회원ID", required=true) @RequestParam String id,
			@ApiParam(value="회원PW", required=true) @RequestParam String password,
			@ApiParam(value="이름", required=true) @RequestParam String name) {
		userJpaRepo.save(User.builder()
				.id(id).password(passwordEncoder.encode(password))
				.name(name).roles(Collections.singletonList("ROLE_USER"))
				.build());
		return responseService.getSuccessResult();
	}
}
