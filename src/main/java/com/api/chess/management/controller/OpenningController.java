package com.api.chess.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.entity.Openning;
import com.api.chess.management.service.OpenningService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(ConfigurationConstants.OPENNING_API_URL)
@Api(value = "Opening", tags = "Opening")
public class OpenningController {

	@Autowired
	private OpenningService openningService;
	
	private static final Logger log = LoggerFactory.getLogger(OpenningController.class);

	@GetMapping
	public ResponseEntity<List<Openning>> getAllOpenning() {
		log.info("User {} calling getAllOpenning service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<List<Openning>>(openningService.getAllOpennings(), HttpStatus.OK);
	}
}
