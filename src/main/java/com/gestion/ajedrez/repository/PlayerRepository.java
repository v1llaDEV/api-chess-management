package com.gestion.ajedrez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.ajedrez.entity.Player;

public interface PlayerRepository extends JpaRepository<Player, Long>{

}
