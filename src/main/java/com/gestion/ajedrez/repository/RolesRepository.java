package com.gestion.ajedrez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.ajedrez.entity.Rol;

public interface RolesRepository extends JpaRepository<Rol, Long> {

}
