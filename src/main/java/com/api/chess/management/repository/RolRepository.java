package com.api.chess.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.chess.management.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

	@Query("Select r from Rol r where r.name = ?1")
	public Rol findByRolName(String rolName);
}
