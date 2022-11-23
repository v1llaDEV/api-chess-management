package com.api.chess.management.service;

import java.util.List;

import com.api.chess.management.entity.Country;

public interface CountryService {

	public List<Country> getAllCountries();

	public Country getCountryById(String id);

	public Country updateCountry(Country country, String id);

	public Country createCountry(Country country);

	public void deleteCountry(String id);

}
