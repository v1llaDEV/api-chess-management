package com.api.chess.management.service;

import java.util.List;

import com.api.chess.management.dto.responses.CountryResponse;
import com.api.chess.management.entity.Country;

// TODO: Auto-generated Javadoc
/**
 * The Interface CountryService.
 */
public interface CountryService {

	/**
	 * Gets the all countries.
	 *
	 * @return the all countries
	 */
	public List<CountryResponse> getAllCountries();

	/**
	 * Gets the country by id.
	 *
	 * @param id the id
	 * @return the country by id
	 */
	public CountryResponse getCountryById(String id);

	/**
	 * Update country.
	 *
	 * @param country the country
	 * @param id the id
	 * @return the country
	 */
	public CountryResponse updateCountry(Country country, String id);

	/**
	 * Creates the country.
	 *
	 * @param country the country
	 * @return the country
	 */
	public CountryResponse createCountry(Country country);

	/**
	 * Delete country.
	 *
	 * @param id the id
	 */
	public void deleteCountry(String id);

}
