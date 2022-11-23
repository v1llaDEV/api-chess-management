package com.api.chess.management.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.api.chess.management.entity.Game;

@Transactional
public interface GameRepository extends JpaRepository<Game, Long> {

	@Modifying
	@Query("Delete from Game g where g.whitePlayer.id in (Select j.id from Player j where j.country.id = ?1)")
	public void deleteWhitePlayerById(Long id);
	
	@Modifying
	@Query("Delete from Game g where g.blackPlayer.id in (Select j.id from Player j where j.country.id = ?1)")
	public void deleteBlackPlayerById(Long id);
}