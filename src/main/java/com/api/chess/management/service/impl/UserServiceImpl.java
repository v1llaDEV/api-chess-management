package com.api.chess.management.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.chess.management.entity.Rol;
import com.api.chess.management.entity.User;
import com.api.chess.management.repository.RolRepository;
import com.api.chess.management.repository.UserRepository;
import com.api.chess.management.service.UserService;
import com.api.chess.management.validators.UserValidator;

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
		User userFound = UserValidator.validateIdParameter(id, userRepository);
		return userFound;
	}

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

	@Override
	public void deleteUser(String id) {
		UserValidator.validateIdParameter(id, userRepository);

		userRepository.deleteById(Long.valueOf(id));
	}

}
