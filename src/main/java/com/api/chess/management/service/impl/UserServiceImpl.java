package com.api.chess.management.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.chess.management.entity.Rol;
import com.api.chess.management.entity.User;
import com.api.chess.management.repository.RolRepository;
import com.api.chess.management.repository.UserRepository;
import com.api.chess.management.service.UserService;
import com.api.chess.management.validators.UserValidator;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 */
@Service

/** The Constant log. */
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The rol repository. */
	@Autowired
	private RolRepository rolRepository;

	/** The b crypt password encoder. */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Gets the user byd id.
	 *
	 * @param id the id
	 * @return the user byd id
	 */
	@Override
	public User getUserBydId(String id) {
		User userFound = UserValidator.validateIdParameter(id, userRepository);
		return userFound;
	}
	
	/**
	 * Gets the user by username.
	 *
	 * @param username the username
	 * @return the user by username
	 */
	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	/**
	 * Update user.
	 *
	 * @param user the user
	 * @param id the id
	 * @return the user
	 */
	@Override
	public User updateUser(User user, String id) {
		UserValidator.validateIdParameter(id, userRepository);
		UserValidator.validateUsernameParameter(user, userRepository);
		List<Rol> rolList = UserValidator.validateRolParameter(user, rolRepository);

		user.setId(Long.valueOf(id));
		user.setRoles(rolList);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return user;
	}

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the user
	 */
	@Override
	public User createUser(User user) {
		List<Rol> rolList = UserValidator.validateRolParameter(user, rolRepository);
		UserValidator.validateUsernameParameter(user, userRepository);

		user.setId(null);
		user.setRoles(rolList);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setCreated(new Date());
		userRepository.save(user);
		return user;
	}

	/**
	 * Delete user.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteUser(String id) {
		UserValidator.validateIdParameter(id, userRepository);
		userRepository.deleteById(Long.valueOf(id));
	}
	
	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 */
	@Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("Usuario no encontrado en la base de datos: {}", username);
            throw new UsernameNotFoundException("El usuario " + username + " no existe");
        } else {
            log.info("Usuario encontrado en la base de datos: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

}
