package com.api.chess.management.service;

import java.util.List;

import com.api.chess.management.entity.Game;

// TODO: Auto-generated Javadoc
/**
 * The Interface GameService.
 */
public interface GameService {
	
	/**
	 * Gets the all countries.
	 *
	 * @return the all countries
	 */
	public List<Game> getAllCountries();

	/**
	 * Gets the game by id.
	 *
	 * @param id the id
	 * @return the game by id
	 */
	public Game getGameById(String id);

	/**
	 * Update game.
	 *
	 * @param Game the game
	 * @param id the id
	 * @return the game
	 */
	public Game updateGame(Game Game, String id);

	/**
	 * Creates the game.
	 *
	 * @param Game the game
	 * @return the game
	 */
	public Game createGame(Game Game);

	/**
	 * Delete game.
	 *
	 * @param id the id
	 */
	public void deleteGame(String id);
}
