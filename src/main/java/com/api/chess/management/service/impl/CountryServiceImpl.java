package com.api.chess.management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.chess.management.dto.responses.CountryResponse;
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
	
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Gets the all countries.
	 *
	 * @return the all countries
	 */
	@Transactional(readOnly = true)
	@Override
	public List<CountryResponse> getAllCountries() {
		List<CountryResponse> responseCountryList = new ArrayList<CountryResponse>();
		
		List<Country> countryList = countryRepository.findAll();
		responseCountryList = countryList.stream().map(country -> modelMapper.map(country, CountryResponse.class))
				.collect(Collectors.toList());
		
		return responseCountryList;
		
	}

	/**
	 * Gets the country by id.
	 *
	 * @param id the id
	 * @return the country by id
	 */
	@Transactional(readOnly = true)
	@Override
	public CountryResponse getCountryById(String id) {
		
		CountryValidator.validateIdParameter(id, countryRepository);
		
		Country country = countryRepository.findById(Long.valueOf(id)).get();

		CountryResponse countryResponse = new CountryResponse();
		countryResponse = modelMapper.map(country, CountryResponse.class);
		
		return countryResponse;
	}

	/**
	 * Update country.
	 *
	 * @param country the country
	 * @param id the id
	 * @return the country
	 */
	@Transactional
	@Override
	public CountryResponse updateCountry(Country country, String id) {		
		CountryValidator.validateIdParameter(id, countryRepository);
		CountryValidator.validateNameParameter(country, countryRepository);

		country.setId(Long.valueOf(id));
		Country countrySaved = countryRepository.save(country);
		
		CountryResponse countryResponse = new CountryResponse();
		countryResponse = modelMapper.map(countrySaved, CountryResponse.class);
		
		return countryResponse;
	}

	/**
	 * Creates the country.
	 *
	 * @param country the country
	 * @return the country
	 */
	@Transactional
	@Override
	public CountryResponse createCountry(Country country) {		
		CountryValidator.validateNameParameter(country, countryRepository);
		country.setId(null);
		Country countrySaved = countryRepository.save(country);
		
		CountryResponse countryResponse = new CountryResponse();
		countryResponse = modelMapper.map(countrySaved, CountryResponse.class);

		return countryResponse;
	}

	/**
	 * Delete country.
	 *
	 * @param id the id
	 */
	@Transactional
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
