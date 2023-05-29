package com.api.chess.management.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.chess.management.dto.responses.UserResponse;
import com.api.chess.management.entity.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	public List<UserResponse> getAllUsers();

	/**
	 * Gets the user byd id.
	 *
	 * @param id the id
	 * @return the user byd id
	 */
	public UserResponse getUserBydId(@PathVariable String id);

	/**
	 * Update user.
	 *
	 * @param user the user
	 * @param id the id
	 * @return the user
	 */
	public UserResponse updateUser(@RequestBody User user, @PathVariable String id);

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public UserResponse createUser(@RequestBody User user);

	/**
	 * Delete user.
	 *
	 * @param id the id
	 */
	public void deleteUser(@PathVariable String id);
	
	/**
	 * Gets the user by username.
	 *
	 * @param username the username
	 * @return the user by username
	 */
	public UserResponse getUserByUsername(String username);
}
