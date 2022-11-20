package com.api.chess.management.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.entity.User;
import com.api.chess.management.exception.ResourceAlreadyExistsException;
import com.api.chess.management.exception.ResourceNotFoundException;
import com.api.chess.management.repository.UserRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(ConfigurationConstants.USER_API_URL)
@Api(value = "User", tags = "User")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserBydId(@PathVariable Long id) {
		User userFound = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
		userRepository.save(userFound);
		return new ResponseEntity<>(userFound, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user, Long id) {
		userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + user.getId()));
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		if(user.getId()!=null) {
			User userFound = userRepository.findById(user.getId()).get();
			if(userFound != null) {
				throw new ResourceAlreadyExistsException("User already exist with id " + user.getId());
			}
		}
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setCreated(new Date());
		userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
		userRepository.deleteById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
