package com.baseball.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.baseball.User;

public interface UserJpaRepo extends JpaRepository<User, Integer>{
	Optional<User> findById(String id);
	
	@Transactional
	void deleteById(String id);
}
