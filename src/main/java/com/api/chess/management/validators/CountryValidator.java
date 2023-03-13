package com.api.chess.management.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.api.chess.management.entity.Country;
import com.api.chess.management.exception.GeneralException;
import com.api.chess.management.exception.ResourceAlreadyExistsException;
import com.api.chess.management.exception.ResourceNotFoundException;
import com.api.chess.management.repository.CountryRepository;

public class CountryValidator {
	
	private static final Logger log = LoggerFactory.getLogger(CountryValidator.class);
	
	public static Country validateIdParameter(String id, CountryRepository countryRepository) {
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

		Country country = countryRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("Country with id: " + id + " doesnt exist"));

		return country;

	}

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

}
