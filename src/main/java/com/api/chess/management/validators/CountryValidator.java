package com.api.chess.management.validators;

import com.api.chess.management.entity.Country;
import com.api.chess.management.exception.GeneralException;
import com.api.chess.management.exception.ResourceAlreadyExistsException;
import com.api.chess.management.exception.ResourceNotFoundException;
import com.api.chess.management.repository.CountryRepository;

public class CountryValidator {
	public static Country validateIdParameter(String id, CountryRepository countryRepository) {
		if (id == null) {
			throw new GeneralException("id parameter is null. Specifiy one.");
		}

		if (!id.toString().chars().allMatch(Character::isDigit)) {
			throw new GeneralException("id parameter is not a number.");
		}

		Country country = countryRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("Country with id: " + id + " doesnt exist"));

		return country;

	}

	public static void validateNameParameter(Country country, CountryRepository countryRepository) {
		if (country.getName() == null || country.getName().isBlank()) {
			throw new ResourceAlreadyExistsException("Country name must be defined");
		}

		Country countryName = countryRepository.findByName(country.getName());
		if (countryName != null) {
			throw new ResourceAlreadyExistsException("Country with name: " + countryName.getName() + " already exists");
		}
	}

}
