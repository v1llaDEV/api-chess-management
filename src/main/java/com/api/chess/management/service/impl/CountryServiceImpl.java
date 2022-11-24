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

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	GameRepository gameRepository;

	@Override
	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}

	@Override
	public Country getCountryById(String id) {
		Country countryFound = CountryValidator.validateIdParameter(id, countryRepository);
		return countryFound;
	}

	@Override
	public Country updateCountry(Country country, String id) {
		CountryValidator.validateIdParameter(id, countryRepository);
		CountryValidator.validateNameParameter(country, countryRepository);

		country.setId(Long.valueOf(id));
		countryRepository.save(country);
		return country;
	}

	@Override
	public Country createCountry(Country country) {

		CountryValidator.validateNameParameter(country, countryRepository);

		country.setId(null);
		countryRepository.save(country);
		return country;
	}

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
