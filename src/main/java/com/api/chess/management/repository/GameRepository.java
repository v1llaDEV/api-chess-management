package com.api.chess.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.chess.management.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

}