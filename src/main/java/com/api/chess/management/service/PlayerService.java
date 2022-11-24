package com.api.chess.management.service;

import java.util.List;

import com.api.chess.management.entity.Player;

public interface PlayerService {
	public List<Player> getAllPlayers();

	public Player getPlayerById(String id);

	public Player updatePlayer(Player Player, String id);

	public Player createPlayer(Player Player);

	public void deletePlayer(String id);
}
