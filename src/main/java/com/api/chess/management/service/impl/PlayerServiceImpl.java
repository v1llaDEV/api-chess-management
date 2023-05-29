package com.api.chess.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.chess.management.entity.Player;
import com.api.chess.management.repository.CountryRepository;
import com.api.chess.management.repository.PlayerRepository;
import com.api.chess.management.service.PlayerService;
import com.api.chess.management.validators.PlayerValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerServiceImpl.
 */
@Service
public class PlayerServiceImpl implements PlayerService {

	/** The player repository. */
	@Autowired
	PlayerRepository playerRepository;

	/** The country repository. */
	@Autowired
	CountryRepository countryRepository;

	/**
	 * Gets the all players.
	 *
	 * @return the all players
	 */
	@Override
	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	/**
	 * Gets the player by id.
	 *
	 * @param id the id
	 * @return the player by id
	 */
	@Override
	public Player getPlayerById(String id) {
		Player playerFound = PlayerValidator.validateIdParameter(id, playerRepository);
		return playerFound;
	}

	/**
	 * Update player.
	 *
	 * @param player the player
	 * @param id the id
	 * @return the player
	 */
	@Override
	public Player updatePlayer(Player player, String id) {
		PlayerValidator.validateIdParameter(id, playerRepository);
		PlayerValidator.validateCountryId(player, countryRepository);

		player.setId(Long.valueOf(id));
		playerRepository.save(player);
		return player;
	}

	/**
	 * Creates the player.
	 *
	 * @param player the player
	 * @return the player
	 */
	@Override
	public Player createPlayer(Player player) {
		PlayerValidator.validateCountryId(player, countryRepository);

		player.setId(null);
		playerRepository.save(player);
		return player;
	}

	/**
	 * Delete player.
	 *
	 * @param id the id
	 */
	@Override
	public void deletePlayer(String id) {
		PlayerValidator.validateIdParameter(id, playerRepository);
		playerRepository.deleteById(Long.valueOf(id));

	}

}
