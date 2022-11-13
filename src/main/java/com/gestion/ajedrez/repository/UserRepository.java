package com.gestion.ajedrez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.ajedrez.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
