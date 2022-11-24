package com.api.chess.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.chess.management.entity.Game;
import com.api.chess.management.repository.GameRepository;
import com.api.chess.management.repository.PlayerRepository;
import com.api.chess.management.repository.ResultRepository;
import com.api.chess.management.service.GameService;
import com.api.chess.management.validators.GameValidator;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	GameRepository gameRepository;

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	ResultRepository resultRepository;

	@Override
	public List<Game> getAllCountries() {
		return gameRepository.findAll();
	}

	@Override
	public Game getGameById(String id) {
		Game gameFound = GameValidator.validateIdParameter(id, gameRepository);
		return gameFound;
	}

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

	@Override
	public Game createGame(Game game) {
		GameValidator.validateWhitePlayerParameter(game, playerRepository);
		GameValidator.validateBlackPlayerParameter(game, playerRepository);
		GameValidator.validateResultParameter(game, resultRepository);

		game.setId(null);
		gameRepository.save(game);
		return game;
	}

	@Override
	public void deleteGame(String id) {
		GameValidator.validateIdParameter(id, gameRepository);
		gameRepository.deleteById(Long.valueOf(id));

	}

}
