package com.api.chess.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.chess.management.entity.Player;
import com.api.chess.management.repository.CountryRepository;
import com.api.chess.management.repository.PlayerRepository;
import com.api.chess.management.service.PlayerService;
import com.api.chess.management.validators.PlayerValidator;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	CountryRepository countryRepository;

	@Override
	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	@Override
	public Player getPlayerById(String id) {
		Player playerFound = PlayerValidator.validateIdParameter(id, playerRepository);
		return playerFound;
	}

	@Override
	public Player updatePlayer(Player player, String id) {
		PlayerValidator.validateIdParameter(id, playerRepository);
		PlayerValidator.validateCountryId(player, countryRepository);

		player.setId(Long.valueOf(id));
		playerRepository.save(player);
		return player;
	}

	@Override
	public Player createPlayer(Player player) {
		PlayerValidator.validateCountryId(player, countryRepository);

		player.setId(null);
		playerRepository.save(player);
		return player;
	}

	@Override
	public void deletePlayer(String id) {
		PlayerValidator.validateIdParameter(id, playerRepository);
		playerRepository.deleteById(Long.valueOf(id));

	}

}
