package com.api.chess.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.entity.Game;
import com.api.chess.management.repository.GameRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(ConfigurationConstants.GAME_API_URL)
@Api(value="Game", tags = "Game")
public class GameController {

	@Autowired
	private GameRepository gameRepository;
	
	@GetMapping("")
	public List<Game> getAllGames(){
		return gameRepository.findAll();
	}
}
