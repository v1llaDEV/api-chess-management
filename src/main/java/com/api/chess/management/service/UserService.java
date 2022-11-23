package com.api.chess.management.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.chess.management.entity.User;

public interface UserService {
	
	public ResponseEntity<List<User>> getAllUsers();
	public ResponseEntity<User> getUserBydId(@PathVariable String id);
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String id);
	public ResponseEntity<User> createUser(@RequestBody User user);
	public ResponseEntity<User> deleteUser(@PathVariable String id);
}
