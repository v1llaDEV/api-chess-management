package com.api.chess.management.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.api.chess.management.entity.Country;

public interface CountryService {

	public ResponseEntity<List<Country>> getAllCountries();
	public ResponseEntity<Country> getCountryById(String id);
	public ResponseEntity<Country> updateCountry(Country country, String id);
	public ResponseEntity<Country> createCountry(Country country);
	public ResponseEntity<Country> deleteCountry(String id);
	
}
