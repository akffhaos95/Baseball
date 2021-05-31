package com.baseball.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

import com.baseball.ListResult;
import com.baseball.ResponseService;
import com.baseball.Game;
import com.baseball.repo.GameJpaRepo;

@Api(tags= {"3.Game"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/game")
public class GameController {
	public final GameJpaRepo gameJpaRepo;
	public final ResponseService responseService;
		
	@ApiOperation(value="경기 리스트 조회", notes="모든 경기를 조회")
	@GetMapping("/games")
	public ListResult<Game> findAllGame() {
		return responseService.getListResult(gameJpaRepo.findAll());
	}
	
    @ApiImplicitParams({
    	@ApiImplicitParam(name="X-AUTH-TOKEN", value="로그인 성공 후 access_token", required=true, dataType="String", paramType="header")
    })
    @ApiOperation(value="경기 단일 조회", notes="회원ID 소속 팀으로 경기들을 조회한다")
    @GetMapping(value="/my_games")
    public ListResult<Game> findGameById(@ApiParam(value="언어", defaultValue="ko") @RequestParam String lang) throws Exception {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String id = authentication.getName();
    	
    	List<Game> res = gameJpaRepo.findByHomeTeam(id);
    	res.addAll(gameJpaRepo.findByAwayTeam(id));
    	return responseService.getListResult(res);
    }
}
