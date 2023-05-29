package com.api.chess.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.entity.User;
import com.api.chess.management.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController
@RequestMapping(ConfigurationConstants.USER_API_URL)
@Api(value = "User", tags = "User")
public class UserController {

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	@ApiOperation(value = "Get all users", response = User[].class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied.") })
	@GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAllUsers() {
		log.info("User {} calling getAllUsers service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return userService.getAllUsers();
	}

	/**
	 * Gets the user byd id.
	 *
	 * @param id the id
	 * @return the user byd id
	 */
	@ApiOperation(value = "Get user by id", response = User.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.")})
	@GetMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public User getUserBydId(@PathVariable String id) {
		log.info("User {} calling getUserBydId service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return userService.getUserBydId(id);
	}

	/**
	 * Update user.
	 *
	 * @param user the user
	 * @param id the id
	 * @return the user
	 */
	@ApiOperation(value = "Update user", response = User.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.")})
	@PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public User updateUser(@RequestBody User user, @PathVariable String id) {
		log.info("User {} calling updateUser service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return userService.updateUser(user, id);

	}

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the user
	 */
	@ApiOperation(value = "Create user", response = User.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.")})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public User createUser(@RequestBody User user) {
		log.info("User {} calling createUser service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return userService.createUser(user);

	}

	/**
	 * Delete user.
	 *
	 * @param id the id
	 */
	@ApiOperation(value = "Delete user", response = Void.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 403, message = "Forbidden. Access Denied."),
			@ApiResponse(code = 404, message = "Not found.")})
	@DeleteMapping(name="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable String id) {
		log.info("User {} calling deleteUser service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		userService.deleteUser(id);
	}
}
