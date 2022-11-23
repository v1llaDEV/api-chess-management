package com.api.chess.management.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.api.chess.management.entity.Player;

@Transactional
public interface PlayerRepository extends JpaRepository<Player, Long>{

	@Modifying
	@Query("Delete from Player p where p.country.id = ?1")
	public void deleteByCountryId(Long id);
}
