package com.api.chess.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.chess.management.entity.Country;
import com.api.chess.management.exception.GeneralException;
import com.api.chess.management.exception.ResourceAlreadyExistsException;
import com.api.chess.management.exception.ResourceNotFoundException;
import com.api.chess.management.repository.CountryRepository;
import com.api.chess.management.repository.GameRepository;
import com.api.chess.management.repository.PlayerRepository;
import com.api.chess.management.service.CountryService;

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
		if (id == null) {
			throw new GeneralException("ID parameter is null. Specifiy one.");
		}

		if (!id.toString().chars().allMatch(Character::isDigit)) {
			throw new GeneralException("ID parameter is not a number.");
		}

		Country countryFound = countryRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("Country not exist with id: " + id));
		return countryFound;
	}

	@Override
	public Country updateCountry(Country country, String id) {
		if (id == null) {
			throw new GeneralException("ID parameter is null. Specifiy one.");
		}

		if (!id.toString().chars().allMatch(Character::isDigit)) {
			throw new GeneralException("ID parameter is not a number.");
		}

		countryRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("Country with id: " + id + " doesnt exist"));

		// Comprobando que el name debe ser único
		if (country.getName() == null || country.getName().isBlank()) {
			throw new ResourceAlreadyExistsException("Country name must be defined");
		}

		Country countryName = countryRepository.findByName(country.getName());
		if (countryName != null) {
			throw new ResourceAlreadyExistsException("Country with name: " + countryName.getName() + " already exists");
		}

		country.setId(Long.valueOf(id));
		country.setName(country.getName());
		countryRepository.save(country);
		return country;
	}

	@Override
	public Country createCountry(Country country) {

		// Comprobando que el id debe ser único
		if (country.getId() != null) {
			throw new GeneralException("ID should not be expecified in country creation");
		}

		// Comprobando que el name debe ser único
		if (country.getName() == null || country.getName().isBlank()) {
			throw new ResourceAlreadyExistsException("Country name must be defined");
		}

		Country countryName = countryRepository.findByName(country.getName());
		if (countryName != null) {
			throw new ResourceAlreadyExistsException("Country with name: " + country.getName() + " already exists");
		}

		country.setName(country.getName());
		countryRepository.save(country);
		return country;
	}

	@Override
	public void deleteCountry(String id) {
		if (id == null || id.isEmpty()) {
			throw new GeneralException("ID parameter is null. Specifiy one.");
		}

		if (!id.toString().chars().allMatch(Character::isDigit)) {
			throw new GeneralException("ID parameter is not a number.");
		}

		countryRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("Country doesnt exist with id: " + id));
		
		//eliminamos las partidas con ese jugador
		gameRepository.deleteWhitePlayerById(Long.valueOf(id));
		gameRepository.deleteBlackPlayerById(Long.valueOf(id));
		
		//eliminamos los jugadores
		playerRepository.deleteByCountryId(Long.valueOf(id));
		
		//eliminamos el país
		countryRepository.deleteById(Long.valueOf(id));
	}

}
