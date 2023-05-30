package com.api.chess.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.chess.management.entity.Game;
import com.api.chess.management.repository.GameRepository;
import com.api.chess.management.repository.PlayerRepository;
import com.api.chess.management.repository.ResultRepository;
import com.api.chess.management.service.GameService;
import com.api.chess.management.validators.GameValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class GameServiceImpl.
 */
@Service
public class GameServiceImpl implements GameService {

	/** The game repository. */
	@Autowired
	GameRepository gameRepository;

	/** The player repository. */
	@Autowired
	PlayerRepository playerRepository;

	/** The result repository. */
	@Autowired
	ResultRepository resultRepository;

	/**
	 * Gets the all countries.
	 *
	 * @return the all countries
	 */
	
	@Transactional(readOnly = true)
	@Override
	public List<Game> getAllCountries() {
		return gameRepository.findAll();
	}

	/**
	 * Gets the game by id.
	 *
	 * @param id the id
	 * @return the game by id
	 */
	@Transactional(readOnly = true)
	@Override
	public Game getGameById(String id) {
		Game gameFound = GameValidator.validateIdParameter(id, gameRepository);
		return gameFound;
	}

	/**
	 * Update game.
	 *
	 * @param game the game
	 * @param id the id
	 * @return the game
	 */
	@Transactional
	@Override
	public Game updateGame(Game game, String id) {
		GameValidator.validateIdParameter(id, gameRepository);
		GameValidator.validateWhitePlayerParameter(game, playerRepository);
		GameValidator.validateBlackPlayerParameter(game, playerRepository);
		GameValidator.validateResultParameter(game, resultRepository);

		game.setId(Long.valueOf(id));
		gameRepository.save(game);
		return game;
	}

	/**
	 * Creates the game.
	 *
	 * @param game the game
	 * @return the game
	 */
	@Transactional
	@Override
	public Game createGame(Game game) {
		GameValidator.validateWhitePlayerParameter(game, playerRepository);
		GameValidator.validateBlackPlayerParameter(game, playerRepository);
		GameValidator.validateResultParameter(game, resultRepository);

		game.setId(null);
		gameRepository.save(game);
		return game;
	}

	/**
	 * Delete game.
	 *
	 * @param id the id
	 */
	@Transactional
	@Override
	public void deleteGame(String id) {
		GameValidator.validateIdParameter(id, gameRepository);
		gameRepository.deleteById(Long.valueOf(id));

	}

}
