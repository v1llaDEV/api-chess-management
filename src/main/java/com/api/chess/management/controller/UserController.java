package com.api.chess.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.entity.Country;
import com.api.chess.management.entity.User;
import com.api.chess.management.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(ConfigurationConstants.USER_API_URL)
@Api(value = "User", tags = "User")
public class UserController {

	@Autowired
	private UserService userService;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@GetMapping
	@ApiOperation(value = "Get all countries", response = Country[].class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Not authorizated to make this operation"),
			@ApiResponse(code = 403, message = "You dont have permissions to make this operation") })
	public ResponseEntity<List<User>> getAllUsers() {
		log.info("User {} calling getAllUsers service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserBydId(@PathVariable String id) {
		log.info("User {} calling getUserBydId service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<User>(userService.getUserBydId(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String id) {
		log.info("User {} calling updateUser service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		log.info("User {} calling createUser service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable String id) {
		log.info("User {} calling deleteUser service",
				SecurityContextHolder.getContext().getAuthentication().getName());
		userService.deleteUser(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
