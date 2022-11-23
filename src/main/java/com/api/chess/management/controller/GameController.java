package com.api.chess.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	private GameService gameService;

	@GetMapping
	public ResponseEntity<List<Game>> getAllGames() {
		return new ResponseEntity<>(gameService.getAllCountries(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Game> getCountryById(@PathVariable String id) {
		return new ResponseEntity<Game>(gameService.getGameById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Game> updateCountry(@RequestBody Game country, @PathVariable String id) {
		return new ResponseEntity<Game>(gameService.updateGame(country, id), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Game> createCountry(@RequestBody Game country) {
		return new ResponseEntity<Game>(gameService.createGame(country), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Game> deleteCountry(@PathVariable String id) {
		gameService.deleteGame(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
