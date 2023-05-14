package com.api.chess.management.validators;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.api.chess.management.entity.Rol;
import com.api.chess.management.entity.Users;
import com.api.chess.management.exception.GeneralException;
import com.api.chess.management.exception.ResourceAlreadyExistsException;
import com.api.chess.management.exception.ResourceNotFoundException;
import com.api.chess.management.repository.RolRepository;
import com.api.chess.management.repository.UserRepository;

public class UserValidator {

	private static final Logger log = LoggerFactory.getLogger(CountryValidator.class);

	public static Users validateIdParameter(String id, UserRepository userRepository) {
		if (id == null) {
			log.info("User {} failed at UserValidator.validateIdParameter because id parameter is null ",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new GeneralException("id parameter is null. Specifiy one.");
		}

		if (!id.toString().chars().allMatch(Character::isDigit)) {
			log.info("User {} failed at UserValidator.validateIdParameter because id parameter is not a number",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new GeneralException("id parameter is not a number.");
		}

		Users user = userRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " doesnt exist"));

		return user;

	}

	public static void validateUsernameParameter(Users user, UserRepository userRepository) {
		if (user.getUsername() == null || user.getUsername().isEmpty()) {
			log.info("User {} failed at UserValidator.validateUsernameParameter because username is null ",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new ResourceAlreadyExistsException("Username must be defined");
		}

		Users usernameFound = userRepository.findByUsername(user.getUsername()).orElse(null);
		if (usernameFound != null) {
			throw new ResourceAlreadyExistsException("User with username: " + user.getUsername() + " already exists");
		}
	}

	public static List<Rol> validateRolParameter(Users user, RolRepository rolRepository) {
		// Comprobando que se le indica al menos 1 rol
		if (user.getRoles() == null || user.getRoles().isEmpty()) {
			log.info("User {} failed at UserValidator.validateRolParameter because rol list is null ",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new ResourceAlreadyExistsException("User definition must have at least 1 rol defined");
		}
		// Buscando el id del rol y creando la lista de roles para asociarselo al
		// usuario
		List<Rol> rolList = new ArrayList<Rol>();
		for (Rol rol : user.getRoles()) {
			Rol rolFound = rolRepository.findByRolName(rol.getName());
			if (rolFound == null) {
				log.info("User {} failed at UserValidator.validateRolParameter because rol with name {} doesnt exists",
						SecurityContextHolder.getContext().getAuthentication().getName(), rol.getName());
				throw new ResourceAlreadyExistsException("Rol " + rol.getName() + " doesnt exists");
			}

			rolList.add(new Rol(rolFound.getId(), rolFound.getName()));

		}

		return rolList;
	}

}
