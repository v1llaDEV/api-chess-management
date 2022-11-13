package com.gestion.ajedrez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.ajedrez.constants.ConfigurationConstants;
import com.gestion.ajedrez.entity.Game;
import com.gestion.ajedrez.repository.GameRepository;

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
