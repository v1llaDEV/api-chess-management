package com.api.chess.management.validators;

import com.api.chess.management.entity.Player;
import com.api.chess.management.exception.GeneralException;
import com.api.chess.management.exception.ResourceAlreadyExistsException;
import com.api.chess.management.exception.ResourceNotFoundException;
import com.api.chess.management.repository.CountryRepository;
import com.api.chess.management.repository.PlayerRepository;

public class PlayerValidator {
	public static Player validateIdParameter(String id, PlayerRepository playerRepository) {
		if (id == null) {
			throw new GeneralException("id parameter is null. Specifiy one.");
		}

		if (!id.toString().chars().allMatch(Character::isDigit)) {
			throw new GeneralException("id parameter is not a number.");
		}

		Player player = playerRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("Player with id: " + id + " doesnt exist"));

		return player;

	}

	public static void validateCountryId(Player player, CountryRepository countryRepository) {
		if (player.getCountry().getId() == null) {
			throw new ResourceAlreadyExistsException("countryId must be defined");
		}

		if (!player.getCountry().getId().toString().chars().allMatch(Character::isDigit)) {
			throw new ResourceAlreadyExistsException("countryId must be a number");
		}

		countryRepository.findById(player.getCountry().getId()).orElseThrow(
				() -> new ResourceNotFoundException("countryId " + player.getCountry().getId() + " doesnt exist"));
	}

}
