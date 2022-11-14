package com.api.chess.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.chess.management.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

}
