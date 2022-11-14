package com.api.chess.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.entity.Country;
import com.api.chess.management.repository.CountryRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(ConfigurationConstants.COUNTRY_API_URL)
@Api(value="Country", tags = "Country")
public class CountryController {

	@Autowired
	private CountryRepository countryRepository;

	@ApiOperation(value="Get all countries", response = Country[].class)
	@ApiResponses(value = {
			 @ApiResponse(code = 200, message = "OK"),
			 @ApiResponse(code = 401, message = "Not authorizated to make this operation"),
			 @ApiResponse(code = 403, message = "You dont have permissions to make this operation")
			 })
	@GetMapping("")
	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}
}
