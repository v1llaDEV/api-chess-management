package com.api.chess.management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.chess.management.entity.Users;

public interface UserRepository extends JpaRepository<Users, Long>{

	@Query("Select u from Users u where u.username = ?1")
	public Optional<Users> findByUsername(String username);
}
