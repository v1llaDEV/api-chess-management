package com.api.chess.management.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.chess.management.entity.Rol;
import com.api.chess.management.entity.User;
import com.api.chess.management.exception.GeneralException;
import com.api.chess.management.exception.ResourceAlreadyExistsException;
import com.api.chess.management.exception.ResourceNotFoundException;
import com.api.chess.management.repository.RolRepository;
import com.api.chess.management.repository.UserRepository;
import com.api.chess.management.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserBydId(String id) {
		if (id == null) {
			throw new GeneralException("ID parameter is null. Specifiy one.");
		}

		if (!id.toString().chars().allMatch(Character::isDigit)) {
			throw new GeneralException("ID parameter is not a number.");
		}

		User userFound = userRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
		return userFound;
	}

	@Override
	public User updateUser(User user, String id) {
		if (id == null) {
			throw new GeneralException("ID parameter is null. Specifiy one.");
		}

		if (!id.toString().chars().allMatch(Character::isDigit)) {
			throw new GeneralException("ID parameter is not a number.");
		}

		User userFound = userRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " doesnt exist"));

		// Comprobando que el username debe ser único
		if (user.getUsername() == null) {
			throw new ResourceAlreadyExistsException("Username must be defined");
		}

		User usernameFound = userRepository.findByUsername(user.getUsername()).orElse(null);
		if (usernameFound == null) {
			throw new ResourceAlreadyExistsException("User with username: " + user.getUsername() + " already exists");
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

		user.setId(Long.valueOf(id));
		user.setRoles(rolList);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setCreated(userFound.getCreated());
		userRepository.save(user);
		return user;
	}

	@Override
	public User createUser(User user) {
		// Comprobando que el id debe ser único
		if (user.getId() != null) {
			new GeneralException("ID should not be expecified in user creation");
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
		return user;
	}

	@Override
	public void deleteUser(String id) {
		if (id == null || id.isEmpty()) {
			throw new GeneralException("ID parameter is null. Specifiy one.");
		}

		if (!id.toString().chars().allMatch(Character::isDigit)) {
			throw new GeneralException("ID parameter is not a number.");
		}

		userRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id: " + id));
		userRepository.deleteById(Long.valueOf(id));
	}

}
