package com.gestion.ajedrez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.ajedrez.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

}
