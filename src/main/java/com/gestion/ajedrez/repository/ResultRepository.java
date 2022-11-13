package com.gestion.ajedrez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.ajedrez.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {

}
