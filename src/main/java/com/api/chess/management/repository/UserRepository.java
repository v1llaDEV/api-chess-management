package com.api.chess.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.chess.management.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("Select u from users u where u.username = ?1")
	public Optional<User> findByUsername(String username);
}
