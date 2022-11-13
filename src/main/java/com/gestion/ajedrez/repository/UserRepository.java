package com.gestion.ajedrez.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.ajedrez.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("Select u from User u where u.username = ?1")
	public Optional<User> findByUsername(String username);
}
