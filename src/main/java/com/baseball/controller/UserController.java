package com.baseball.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baseball.CommonResult;
import com.baseball.ListResult;
import com.baseball.ResponseService;
import com.baseball.SingleResult;
import com.baseball.User;
import com.baseball.repo.UserJpaRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags= {"2.User"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserJpaRepo userJpaRepo;
    private final ResponseService responseService;
    
    @GetMapping("/hello")
    public SingleResult<String> hello() {
		return responseService.getSingleResult("hello");
    }
    
    @ApiImplicitParams({
    	@ApiImplicitParam(name="X-AUTH-TOKEN", value="로그인 성공 후 access_token", required=true, dataType="String", paramType="header")
    })
    @ApiOperation(value="회원 리스트 조회", notes="모든 회원을 조회")
    @GetMapping(value="/users")
    public ListResult<User> findAllUser() {
    	return responseService.getListResult(userJpaRepo.findAll());
    }
    
    @ApiImplicitParams({
    	@ApiImplicitParam(name="X-AUTH-TOKEN", value="로그인 성공 후 access_token", required=true, dataType="String", paramType="header")
    })
    @ApiOperation(value="회원 단일 조회", notes="회원ID로 회원을 조회한다")
    @GetMapping(value="/user")
    public SingleResult<User> findUserById(@ApiParam(value="언어", defaultValue="ko") @RequestParam String lang) throws Exception {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String id = authentication.getName();
    	return responseService.getSingleResult(userJpaRepo.findById(id).orElseThrow(Exception::new));
    }
    
    @ApiImplicitParams({
    	@ApiImplicitParam(name="X-AUTH-TOKEN", value="로그인 성공 후 access_token", required=true, dataType="String", paramType="header")
    })
    @ApiOperation(value="회원 수정", notes="회원 정보를 수정")
    @PutMapping(value="/user")
    public SingleResult<User> modifyUser(
    		@ApiParam(value="회원ID", required=true) @RequestParam String id,
    		@ApiParam(value="회원 이름", required=true) @RequestParam String name) {
    	User user = User.builder()
    			.id(id).name(name).build();
    	return responseService.getSingleResult(userJpaRepo.save(user));
    }
    
    @ApiImplicitParams({
    	@ApiImplicitParam(name="X-AUTH-TOKEN", value="로그인 성공 후 access_token", required=true, dataType="String", paramType="header")
    })
    @ApiOperation(value="회원 삭제", notes="userId로 회원정보를 삭제한다")
    @DeleteMapping(value="/user/{id}")
    public CommonResult deleteUser(@ApiParam(value="회원번호", required=true) @PathVariable String id) {
    	userJpaRepo.deleteById(id);
    	return responseService.getSuccessResult();
    }
}