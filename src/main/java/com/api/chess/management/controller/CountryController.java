package com.api.chess.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.api.chess.management.entity.Country;
import com.api.chess.management.service.CountryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ConfigurationConstants.COUNTRY_API_URL)
@Api(value = "Country", tags = "Country")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@ApiOperation(value = "Get all countries", response = Country[].class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Not authorizated to make this operation"),
			@ApiResponse(code = 403, message = "You dont have permissions to make this operation") })
	@GetMapping
	public ResponseEntity<List<Country>> getAllCountries() {
		return countryService.getAllCountries();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable String id) {
		return countryService.getCountryById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Country> updateCountry(@RequestBody Country country, @PathVariable String id) {
		return countryService.updateCountry(country, id);

	}

	@PostMapping
	public ResponseEntity<Country> createCountry(@RequestBody Country country) {
		return countryService.createCountry(country);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Country> deleteCountry(@PathVariable String id) {
		return countryService.deleteCountry(id);
	}
}
