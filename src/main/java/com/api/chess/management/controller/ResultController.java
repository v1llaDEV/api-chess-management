package com.api.chess.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.entity.Result;
import com.api.chess.management.service.ResultService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

// TODO: Auto-generated Javadoc
/**
 * The Class ResultController.
 */
@RestController
@RequestMapping(ConfigurationConstants.RESULT_API_URL)
@Api(value = "Result", tags = "Result")
public class ResultController {

	/** The result service. */
	@Autowired
	private ResultService resultService;
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(ResultController.class);

	/**
	 * Gets the all results.
	 *
	 * @return the all results
	 */
	@ApiOperation(value = "Get all results", response = Result[].class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied.") })
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Result> getAllResults() {
		log.info("User {} calling getAllResults service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return resultService.getAllResults();
	}
}
