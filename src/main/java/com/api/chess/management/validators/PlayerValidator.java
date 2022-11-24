package com.api.chess.management.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.api.chess.management.entity.Player;
import com.api.chess.management.exception.GeneralException;
import com.api.chess.management.exception.ResourceAlreadyExistsException;
import com.api.chess.management.exception.ResourceNotFoundException;
import com.api.chess.management.repository.CountryRepository;
import com.api.chess.management.repository.PlayerRepository;

public class PlayerValidator {
	
	private static final Logger log = LoggerFactory.getLogger(CountryValidator.class);
	
	public static Player validateIdParameter(String id, PlayerRepository playerRepository) {
		if (id == null) {
			log.info("User {} failed at PlayerValidator.validateIdParameter because id parameter is null ",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new GeneralException("id parameter is null. Specifiy one.");
		}

		if (!id.toString().chars().allMatch(Character::isDigit)) {
			log.info("User {} failed at PlayerValidator.validateIdParameter because id parameter is not a number",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new GeneralException("id parameter is not a number.");
		}

		Player player = playerRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("Player with id: " + id + " doesnt exist"));

		return player;

	}

	public static void validateCountryId(Player player, CountryRepository countryRepository) {
		if (player.getCountry().getId() == null) {
			log.info("User {} failed at PlayerValidator.validateCountryId because id parameter is null ",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new ResourceAlreadyExistsException("countryId must be defined");
		}

		if (!player.getCountry().getId().toString().chars().allMatch(Character::isDigit)) {
			log.info("User {} failed at PlayerValidator.validateCountryId because id parameter is not a number",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new ResourceAlreadyExistsException("countryId must be a number");
		}

		countryRepository.findById(player.getCountry().getId()).orElseThrow(
				() -> new ResourceNotFoundException("countryId " + player.getCountry().getId() + " doesnt exist"));
	}

}
