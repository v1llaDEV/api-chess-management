package com.api.chess.management.controller;

import java.util.List;

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
import com.api.chess.management.entity.Country;
import com.api.chess.management.service.CountryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class CountryController.
 */
@RestController
@RequestMapping(ConfigurationConstants.COUNTRY_API_URL)
@Slf4j
@Api(value = "Country", tags = "Country")
public class CountryController {

	/** The country service. */
	@Autowired
	private CountryService countryService;

	/**
	 * Gets the all countries.
	 *
	 * @return the all countries
	 */
	@ApiOperation(value = "Get all countries", response = Country[].class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied.") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Country> getAllCountries() {

		log.info("User {} calling getAllCountries service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return countryService.getAllCountries();
	}

	/**
	 * Gets the country by id.
	 *
	 * @param id the id
	 * @return the country by id
	 */
	@ApiOperation(value = "Get country by id", response = Country.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.")})
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Country getCountryById(@PathVariable String id) {
		log.info("User {} calling getCountryById service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return countryService.getCountryById(id);
	}

	/**
	 * Update country.
	 *
	 * @param country the country
	 * @param id the id
	 * @return the country
	 */
	@ApiOperation(value = "Update country", response = Country.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.")})
	@PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Country updateCountry(@RequestBody Country country, @PathVariable String id) {
		log.info("User {} calling updateCountry service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return countryService.updateCountry(country, id);

	}

	/**
	 * Creates the country.
	 *
	 * @param country the country
	 * @return the country
	 */
	@ApiOperation(value = "Create country", response = Country.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.")})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Country createCountry(@RequestBody Country country) {
		log.info("User {} calling createCountry service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return countryService.createCountry(country);

	}

	/**
	 * Delete country.
	 *
	 * @param id the id
	 */
	@ApiOperation(value = "Delete country", response = Void.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.")})
	@DeleteMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteCountry(@PathVariable String id) {
		log.info("User {} calling deleteCountry service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		countryService.deleteCountry(id);
	}
}
