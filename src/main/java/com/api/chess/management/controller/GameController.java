package com.api.chess.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.entity.Game;
import com.api.chess.management.service.GameService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ConfigurationConstants.GAME_API_URL)
@Api(value = "Game", tags = "Game")
public class GameController {
	
	private static final Logger log = LoggerFactory.getLogger(GameController.class);

	@Autowired
	private GameService gameService;

	@ApiOperation(value = "Get all games", response = Game[].class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied.") })
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Game> getAllGames() {
		log.info("User {} calling getAllGames service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return gameService.getAllCountries();
	}

	@ApiOperation(value = "Get game by id", response = Game.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.")})
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Game getGameById(@PathVariable String id) {
		log.info("User {} calling getGameById service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return gameService.getGameById(id);
	}

	@ApiOperation(value = "Get game", response = Game.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.")})
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Game updateGame(@RequestBody Game country, @PathVariable String id) {
		log.info("User {} calling updateGame service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return gameService.updateGame(country, id);

	}

	@ApiOperation(value = "Create game", response = Game.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.")})
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Game createGame(@RequestBody Game country) {
		log.info("User {} calling createGame service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return gameService.createGame(country);

	}

	@ApiOperation(value = "Delete", response = Void.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.")})
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteGame(@PathVariable String id) {
		log.info("User {} calling deleteGame service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		gameService.deleteGame(id);
	}
}
