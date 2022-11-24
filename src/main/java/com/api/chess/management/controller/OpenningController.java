package com.api.chess.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.entity.Openning;
import com.api.chess.management.service.OpenningService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ConfigurationConstants.OPENNING_API_URL)
@Api(value = "Opening", tags = "Opening")
public class OpenningController {

	@Autowired
	private OpenningService openningService;
	
	private static final Logger log = LoggerFactory.getLogger(OpenningController.class);

	@ApiOperation(value = "Get all opennings", response = Openning[].class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied.") })
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Openning> getAllOpennings() {
		log.info("User {} calling getAllOpennings service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return openningService.getAllOpennings();
	}
}
