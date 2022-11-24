package com.api.chess.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.entity.Game;
import com.api.chess.management.service.GameService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(ConfigurationConstants.GAME_API_URL)
@Api(value = "Game", tags = "Game")
public class GameController {
	
	private static final Logger log = LoggerFactory.getLogger(GameController.class);

	@Autowired
	private GameService gameService;

	@GetMapping
	public ResponseEntity<List<Game>> getAllGames() {
		log.info("User {} calling getAllGames service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<>(gameService.getAllCountries(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Game> getCountryById(@PathVariable String id) {
		log.info("User {} calling getCountryById service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<Game>(gameService.getGameById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Game> updateCountry(@RequestBody Game country, @PathVariable String id) {
		log.info("User {} calling updateCountry service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<Game>(gameService.updateGame(country, id), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Game> createCountry(@RequestBody Game country) {
		log.info("User {} calling createCountry service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<Game>(gameService.createGame(country), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Game> deleteCountry(@PathVariable String id) {
		log.info("User {} calling deleteCountry service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		gameService.deleteGame(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
