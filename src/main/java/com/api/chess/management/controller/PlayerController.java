package com.api.chess.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.api.chess.management.entity.Player;
import com.api.chess.management.service.PlayerService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(ConfigurationConstants.PLAYER_API_URL)
@Api(value = "Player", tags = "Player")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

	@GetMapping
	public ResponseEntity<List<Player>> getAllPlayers() {
		log.info("Recogiendo jugadores");
		return new ResponseEntity<List<Player>>(playerService.getAllPlayers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Player> getPlayerById(@PathVariable String id) {
		return new ResponseEntity<Player>(playerService.getPlayerById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Player> updatePlayer(@RequestBody Player player, @PathVariable String id) {
		return new ResponseEntity<Player>(playerService.updatePlayer(player, id), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
		return new ResponseEntity<Player>(playerService.createPlayer(player), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Player> deletePlayer(@PathVariable String id) {
		playerService.deletePlayer(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
