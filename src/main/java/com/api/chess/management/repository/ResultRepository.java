package com.api.chess.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.chess.management.entity.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {

}
