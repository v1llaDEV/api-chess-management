package com.api.chess.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.chess.management.entity.Country;
import com.api.chess.management.repository.CountryRepository;
import com.api.chess.management.repository.GameRepository;
import com.api.chess.management.repository.PlayerRepository;
import com.api.chess.management.service.CountryService;
import com.api.chess.management.validators.CountryValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class CountryServiceImpl.
 */
@Service
public class CountryServiceImpl implements CountryService {

	/** The country repository. */
	@Autowired
	CountryRepository countryRepository;

	/** The player repository. */
	@Autowired
	PlayerRepository playerRepository;

	/** The game repository. */
	@Autowired
	GameRepository gameRepository;

	/**
	 * Gets the all countries.
	 *
	 * @return the all countries
	 */
	@Override
	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}

	/**
	 * Gets the country by id.
	 *
	 * @param id the id
	 * @return the country by id
	 */
	@Override
	public Country getCountryById(String id) {
		Country countryFound = CountryValidator.validateIdParameter(id, countryRepository);
		return countryFound;
	}

	/**
	 * Update country.
	 *
	 * @param country the country
	 * @param id the id
	 * @return the country
	 */
	@Override
	public Country updateCountry(Country country, String id) {
		CountryValidator.validateIdParameter(id, countryRepository);
		CountryValidator.validateNameParameter(country, countryRepository);

		country.setId(Long.valueOf(id));
		countryRepository.save(country);
		return country;
	}

	/**
	 * Creates the country.
	 *
	 * @param country the country
	 * @return the country
	 */
	@Override
	public Country createCountry(Country country) {

		CountryValidator.validateNameParameter(country, countryRepository);

		country.setId(null);
		countryRepository.save(country);
		return country;
	}

	/**
	 * Delete country.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteCountry(String id) {
		CountryValidator.validateIdParameter(id, countryRepository);

		// eliminamos las partidas con ese jugador
		gameRepository.deleteWhitePlayerById(Long.valueOf(id));
		gameRepository.deleteBlackPlayerById(Long.valueOf(id));

		// eliminamos los jugadores
		playerRepository.deleteByCountryId(Long.valueOf(id));

		// eliminamos el país
		countryRepository.deleteById(Long.valueOf(id));
	}

}
