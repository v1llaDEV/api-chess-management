package com.api.chess.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.api.chess.management.entity.Player;
import com.api.chess.management.service.PlayerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ConfigurationConstants.PLAYER_API_URL)
@Api(value = "Player", tags = "Player")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

	@ApiOperation(value = "Get all players", response = Player[].class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied.") })
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Player> getAllPlayers() {
		log.info("User {} calling getAllPlayers service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return playerService.getAllPlayers();
	}

	@ApiOperation(value = "Get player by id", response = Player.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.") })
	@GetMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Player getPlayerById(@PathVariable String id) {
		log.info("User {} calling getPlayerById service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return playerService.getPlayerById(id);
	}

	@ApiOperation(value = "Update player", response = Player.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.") })
	@PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Player updatePlayer(@RequestBody Player player, @PathVariable String id) {
		log.info("User {} calling updatePlayer service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return playerService.updatePlayer(player, id);

	}

	@ApiOperation(value = "Create player", response = Player.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Player createPlayer(@RequestBody Player player) {
		log.info("User {} calling createPlayer service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return playerService.createPlayer(player);

	}

	@ApiOperation(value = "Delete player", response = Void.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.") })
	@DeleteMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void deletePlayer(@PathVariable String id) {
		log.info("User {} calling deletePlayer service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		playerService.deletePlayer(id);
	}
}
