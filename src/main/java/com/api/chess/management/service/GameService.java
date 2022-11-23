package com.api.chess.management.service;

import java.util.List;

import com.api.chess.management.entity.Game;

public interface GameService {
	public List<Game> getAllCountries();

	public Game getGameById(String id);

	public Game updateGame(Game Game, String id);

	public Game createGame(Game Game);

	public void deleteGame(String id);
}
