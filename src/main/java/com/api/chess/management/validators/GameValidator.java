package com.api.chess.management.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.api.chess.management.entity.Game;
import com.api.chess.management.exception.GeneralException;
import com.api.chess.management.exception.ResourceAlreadyExistsException;
import com.api.chess.management.exception.ResourceNotFoundException;
import com.api.chess.management.repository.GameRepository;
import com.api.chess.management.repository.PlayerRepository;
import com.api.chess.management.repository.ResultRepository;

public class GameValidator {
	
	private static final Logger log = LoggerFactory.getLogger(CountryValidator.class);
	
	public static Game validateIdParameter(String id, GameRepository gameRepository) {
		if (id == null) {
			log.info("User {} failed at GameValidator.validateIdParameter because id parameter is null ",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new GeneralException("ID parameter is null. Specifiy one.");
		}

		if (!id.toString().chars().allMatch(Character::isDigit)) {
			log.info("User {} failed at GameValidator.validateIdParameter because id parameter is not a number",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new GeneralException("ID parameter is not a number.");
		}

		Game game = gameRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("Game with id: " + id + " doesnt exist"));

		return game;
	}

	public static void validateResultParameter(Game game, ResultRepository resultRepository) {
		if (game.getResult().getId() == null) {
			log.info("User {} failed at GameValidator.validateResultParameter because id parameter is null ",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new ResourceAlreadyExistsException("Result id must be defined");
		}

		resultRepository.findById(game.getResult().getId()).orElseThrow(
				() -> new ResourceNotFoundException("Result id: " + game.getResult().getId() + " doesnt exist"));
	}

	public static void validateBlackPlayerParameter(Game game, PlayerRepository playerRepository) {
		if (game.getBlackPlayer().getId() == null) {
			log.info("User {} failed at GameValidator.validateBlackPlayerParameter because id parameter is null",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new ResourceAlreadyExistsException("Black player id must be defined");
		}

		if (!game.getBlackPlayer().getId().toString().chars().allMatch(Character::isDigit)) {
			log.info("User {} failed at GameValidator.validateBlackPlayerParameter because id parameter is not a number",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new ResourceAlreadyExistsException("Black player id must be a number");
		}

		playerRepository.findById(game.getBlackPlayer().getId()).orElseThrow(() -> new ResourceNotFoundException(
				"Black player id: " + game.getWhitePlayer().getId() + " doesnt exist"));
	}

	public static void validateWhitePlayerParameter(Game game, PlayerRepository playerRepository) {
		if (game.getWhitePlayer().getId() == null) {
			log.info("User {} failed at GameValidator.validateWhitePlayerParameter because id parameter is null",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new ResourceAlreadyExistsException("White player id must be defined");
		}

		if (!game.getWhitePlayer().getId().toString().chars().allMatch(Character::isDigit)) {
			log.info("User {} failed at GameValidator.validateWhitePlayerParameter because id parameter is not a number",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new ResourceAlreadyExistsException("White player id must be a number");
		}

		playerRepository.findById(game.getWhitePlayer().getId()).orElseThrow(() -> new ResourceNotFoundException(
				"White player id: " + game.getWhitePlayer().getId() + " doesnt exist"));
	}
}
