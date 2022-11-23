package com.api.chess.management.validators;

import com.api.chess.management.entity.Game;
import com.api.chess.management.exception.GeneralException;
import com.api.chess.management.exception.ResourceAlreadyExistsException;
import com.api.chess.management.exception.ResourceNotFoundException;
import com.api.chess.management.repository.GameRepository;
import com.api.chess.management.repository.PlayerRepository;
import com.api.chess.management.repository.ResultRepository;

public class GameValidator {
	public static void validateIdParameter(String id, GameRepository gameRepository) {
		if (id == null) {
			throw new GeneralException("ID parameter is null. Specifiy one.");
		}

		if (!id.toString().chars().allMatch(Character::isDigit)) {
			throw new GeneralException("ID parameter is not a number.");
		}

		gameRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("Game with id: " + id + " doesnt exist"));
	}

	public static void validateResultParameter(Game game, ResultRepository resultRepository) {
		if (game.getResult().getId() == null) {
			throw new ResourceAlreadyExistsException("Result id must be defined");
		}
		
		resultRepository.findById(game.getResult().getId()).orElseThrow(() -> new ResourceNotFoundException("Result id:" + game.getResult().getId() + " doesnt exist"));
	}

	public static void validateBlackPlayerParameter(Game game, PlayerRepository playerRepository) {
		if (game.getBlackPlayer().getId() == null) {
			throw new ResourceAlreadyExistsException("Black player id must be defined");
		}
		
		if (!game.getBlackPlayer().getId().toString().chars().allMatch(Character::isDigit) ) {
			throw new ResourceAlreadyExistsException("Black player id must be a number");
		}
		
		playerRepository.findById(game.getBlackPlayer().getId()).orElseThrow(() -> new ResourceNotFoundException("Black player id:" + game.getWhitePlayer().getId() + " doesnt exist"));
	}

	public static void validateWhitePlayerParameter(Game game, PlayerRepository playerRepository) {
		if (game.getWhitePlayer().getId() == null ) {
			throw new ResourceAlreadyExistsException("White player id must be defined");
		}
		
		if (!game.getWhitePlayer().getId().toString().chars().allMatch(Character::isDigit) ) {
			throw new ResourceAlreadyExistsException("White player id must be a number");
		}
		
		playerRepository.findById(game.getWhitePlayer().getId()).orElseThrow(() -> new ResourceNotFoundException("White player id:" + game.getWhitePlayer().getId() + " doesnt exist"));
	}
}
