package com.api.chess.management.controller;

import java.util.ArrayList;
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
import com.api.chess.management.entity.Rol;
import com.api.chess.management.entity.User;
import com.api.chess.management.exception.ResourceAlreadyExistsException;
import com.api.chess.management.exception.ResourceNotFoundException;
import com.api.chess.management.repository.RolRepository;
import com.api.chess.management.repository.UserRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(ConfigurationConstants.USER_API_URL)
@Api(value = "User", tags = "User")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RolRepository rolRepository;

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

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
		User userFound = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " doesnt exist"));
		
		// Comprobando que el username debe ser único
		if (user.getUsername() == null ) {
			throw new ResourceAlreadyExistsException("Username must be defined");
		}
		
		User usernameFound = userRepository.findByUsername(user.getUsername()).orElse(null);
		if (usernameFound == null) {
			throw new ResourceAlreadyExistsException(
					"User with username: " + user.getUsername() + " already exists");
		}
		
		// Comprobando que se le indica al menos 1 rol
		if (user.getRoles() == null || user.getRoles().isEmpty()) {
			throw new ResourceAlreadyExistsException("User definition must have at least 1 rol defined");
		}

		// Buscando el id del rol y creando la lista de roles para asociarselo al
		// usuario
		List<Rol> rolList = new ArrayList<Rol>();
		for (Rol rol : user.getRoles()) {
			Rol rolFound = rolRepository.findByRolName(rol.getName());
			if (rolFound == null) {
				throw new ResourceAlreadyExistsException("Rol " + rol.getName() + " doesnt exist");
			}

			rolList.add(new Rol(rolFound.getId(), rolFound.getName()));

		}
		
		user.setId(id);
		user.setRoles(rolList);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setCreated(userFound.getCreated());
		userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		// Comprobando que el id debe ser único
		if (user.getId() != null) {
			userRepository.findById(user.getId()).orElseThrow(
					() -> new ResourceAlreadyExistsException("User ID already exist with id " + user.getId()));
		}

		// Comprobando que el username debe ser único
		if (user.getUsername() != null) {
			User userFound = userRepository.findByUsername(user.getUsername()).orElse(null);
			if (userFound != null) {
				throw new ResourceAlreadyExistsException(
						"User with username: " + user.getUsername() + " already exists");
			}
		}

		// Comprobando que se le indica al menos 1 rol
		if (user.getRoles() == null || user.getRoles().isEmpty()) {
			throw new ResourceAlreadyExistsException("User definition must have at least 1 rol defined");
		}

		// Buscando el id del rol y creando la lista de roles para asociarselo al
		// usuario
		List<Rol> rolList = new ArrayList<Rol>();
		for (Rol rol : user.getRoles()) {
			Rol rolFound = rolRepository.findByRolName(rol.getName());
			if (rolFound == null) {
				throw new ResourceAlreadyExistsException("Rol " + rol.getName() + " doesnt exist");
			}

			rolList.add(new Rol(rolFound.getId(), rolFound.getName()));

		}

		user.setRoles(rolList);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setCreated(new Date());
		userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
		userRepository.deleteById(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
