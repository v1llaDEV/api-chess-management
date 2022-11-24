package com.api.chess.management.validators;

import java.util.ArrayList;
import java.util.List;

import com.api.chess.management.entity.Rol;
import com.api.chess.management.entity.User;
import com.api.chess.management.exception.GeneralException;
import com.api.chess.management.exception.ResourceAlreadyExistsException;
import com.api.chess.management.exception.ResourceNotFoundException;
import com.api.chess.management.repository.RolRepository;
import com.api.chess.management.repository.UserRepository;

public class UserValidator {
	public static User validateIdParameter(String id, UserRepository userRepository) {
		if (id == null) {
			throw new GeneralException("id parameter is null. Specifiy one.");
		}

		if (!id.toString().chars().allMatch(Character::isDigit)) {
			throw new GeneralException("id parameter is not a number.");
		}

		User user = userRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " doesnt exist"));

		return user;

	}

	public static void validateUsernameParameter(User user, UserRepository userRepository) {
		if (user.getUsername() == null) {
			throw new ResourceAlreadyExistsException("Username must be defined");
		}

		User usernameFound = userRepository.findByUsername(user.getUsername()).orElse(null);
		if (usernameFound != null) {
			throw new ResourceAlreadyExistsException("User with username: " + user.getUsername() + " already exists");
		}
	}

	public static List<Rol> validateRolParameter(User user, RolRepository rolRepository) {
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

		return rolList;
	}

}
