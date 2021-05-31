package com.baseball.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baseball.Game;

public interface GameJpaRepo extends JpaRepository<Game, Integer>{

	List<Game> findByHomeTeam(String id);
	List<Game> findByAwayTeam(String id);
}
