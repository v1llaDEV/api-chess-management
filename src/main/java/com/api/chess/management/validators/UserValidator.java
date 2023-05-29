package com.api.chess.management.validators;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.api.chess.management.entity.Rol;
import com.api.chess.management.entity.User;
import com.api.chess.management.exception.GeneralException;
import com.api.chess.management.exception.ResourceAlreadyExistsException;
import com.api.chess.management.exception.ResourceNotFoundException;
import com.api.chess.management.repository.RolRepository;
import com.api.chess.management.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class UserValidator.
 */
@Slf4j
public class UserValidator {

	/**
	 * Validate id parameter.
	 *
	 * @param id the id
	 * @param userRepository the user repository
	 * @return the user
	 */
	public static User validateIdParameter(String id, UserRepository userRepository) {
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

		User user = userRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " doesnt exist"));

		return user;

	}

	/**
	 * Validate username parameter.
	 *
	 * @param user the user
	 * @param userRepository the user repository
	 */
	public static void validateUsernameParameter(User user, UserRepository userRepository) {
		if (user.getUsername() == null || user.getUsername().isEmpty()) {
			log.info("User {} failed at UserValidator.validateUsernameParameter because username is null ",
					SecurityContextHolder.getContext().getAuthentication().getName());
			throw new ResourceAlreadyExistsException("Username must be defined");
		}

		User usernameFound = userRepository.findByUsername(user.getUsername());
		if (usernameFound != null) {
			throw new ResourceAlreadyExistsException("User with username: " + user.getUsername() + " already exists");
		}
	}

	/**
	 * Validate rol parameter.
	 *
	 * @param user the user
	 * @param rolRepository the rol repository
	 * @return the list
	 */
	public static List<Rol> validateRolParameter(User user, RolRepository rolRepository) {
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
