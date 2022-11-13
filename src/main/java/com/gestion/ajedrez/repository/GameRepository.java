package com.gestion.ajedrez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.ajedrez.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

}