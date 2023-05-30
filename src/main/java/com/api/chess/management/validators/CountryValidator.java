package com.api.chess.management.validators;

import org.springframework.security.core.context.SecurityContextHolder;

import com.api.chess.management.entity.Country;
import com.api.chess.management.exception.GeneralException;
import com.api.chess.management.exception.ResourceAlreadyExistsException;
import com.api.chess.management.exception.ResourceNotFoundException;
import com.api.chess.management.repository.CountryRepository;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class CountryValidator.
 */

/** The Constant log. */

/** The Constant log. */
@Slf4j
public class CountryValidator {
	

	/**
	 * Validate id parameter.
	 *
	 * @param id the id
	 */
	public static void validateIdParameter(String id, CountryRepository countryRepository) {
		if (id == null) {
			log.info("User {} failed at CountryValidator.validateIdParameter because id parameter is null ",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new GeneralException("id parameter is null. Specifiy one.");
		}

		if (!id.toString().chars().allMatch(Character::isDigit)) {
			log.info("User {} failed at CountryValidator.validateIdParameter because id parameter is not a number",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new GeneralException("id parameter is not a number.");
		}
		
		countryRepository.findById(Long.valueOf(id))
		.orElseThrow(() -> new ResourceNotFoundException("Country with id: " + id + " doesnt exist"));

	}

	/**
	 * Validate name parameter.
	 *
	 * @param country the country
	 * @param countryRepository the country repository
	 */
	public static void validateNameParameter(Country country, CountryRepository countryRepository) {
		if (country.getName() == null || country.getName().isEmpty()) {
			log.info("User {} failed at CountryValidator.validateNameParameter because name is null or empty",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new ResourceAlreadyExistsException("Country name must be defined");
		}

		Country countryName = countryRepository.findByName(country.getName());
		if (countryName != null) {
			log.info("User {} failed at CountryValidator.validateNameParameter because name already exists",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new ResourceAlreadyExistsException("Country with name: " + countryName.getName() + " already exists");
		}
	}
	
	public static ResourceNotFoundException throwExcepctionForCountryNotFound(String id){
		return new ResourceNotFoundException("Country with id: " + id + " doesnt exist");
	}

}
