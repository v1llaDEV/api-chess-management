package com.api.chess.management.service;

import java.util.List;

import com.api.chess.management.entity.Player;

// TODO: Auto-generated Javadoc
/**
 * The Interface PlayerService.
 */
public interface PlayerService {
	
	/**
	 * Gets the all players.
	 *
	 * @return the all players
	 */
	public List<Player> getAllPlayers();

	/**
	 * Gets the player by id.
	 *
	 * @param id the id
	 * @return the player by id
	 */
	public Player getPlayerById(String id);

	/**
	 * Update player.
	 *
	 * @param Player the player
	 * @param id the id
	 * @return the player
	 */
	public Player updatePlayer(Player Player, String id);

	/**
	 * Creates the player.
	 *
	 * @param Player the player
	 * @return the player
	 */
	public Player createPlayer(Player Player);

	/**
	 * Delete player.
	 *
	 * @param id the id
	 */
	public void deletePlayer(String id);
}
