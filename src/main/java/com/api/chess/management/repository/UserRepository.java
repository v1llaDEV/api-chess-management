package com.api.chess.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.chess.management.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsername(String username);
}
