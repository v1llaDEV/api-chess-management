package com.gestion.ajedrez.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.ajedrez.constants.ConfigurationConstants;
import com.gestion.ajedrez.entity.Player;
import com.gestion.ajedrez.repository.PlayerRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(ConfigurationConstants.PLAYER_API_URL)
@Api(value="Player", tags = "Player")
public class PlayerController {

	@Autowired
	private PlayerRepository playerRepository;
	
	private static final Logger log = LoggerFactory.getLogger(PlayerController.class);
	
	
	@GetMapping("")
	public List<Player> getAllPlayers(){
		log.info("Recogiendo jugadores");
		return playerRepository.findAll();
	}
}
