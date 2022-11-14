package com.api.chess.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.chess.management.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{

}