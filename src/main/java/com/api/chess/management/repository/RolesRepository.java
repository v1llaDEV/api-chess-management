package com.api.chess.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.chess.management.entity.Rol;

public interface RolesRepository extends JpaRepository<Rol, Long> {

}
