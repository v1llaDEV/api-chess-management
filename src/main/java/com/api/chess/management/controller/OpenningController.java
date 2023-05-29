package com.api.chess.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class OpenningController.
 */
@RestController
@RequestMapping(ConfigurationConstants.OPENNING_API_URL)
@Slf4j
@Api(value = "Opening", tags = "Opening")
public class OpenningController {

	/** The openning service. */
	@Autowired
	private OpenningService openningService;

	/**
	 * Gets the all opennings.
	 *
	 * @return the all opennings
	 */
	@ApiOperation(value = "Get all opennings", response = Openning[].class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied.") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Openning> getAllOpennings() {
		log.info("User {} calling getAllOpennings service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return openningService.getAllOpennings();
	}
}
