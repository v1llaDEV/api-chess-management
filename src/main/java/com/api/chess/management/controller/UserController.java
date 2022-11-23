package com.api.chess.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	@GetMapping
	@ApiOperation(value="Get all countries", response = Country[].class)
	@ApiResponses(value = {
			 @ApiResponse(code = 200, message = "OK"),
			 @ApiResponse(code = 401, message = "Not authorizated to make this operation"),
			 @ApiResponse(code = 403, message = "You dont have permissions to make this operation")
		})
	public ResponseEntity<List<User>> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserBydId(@PathVariable String id) {
		return userService.getUserBydId(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String id) {
		return userService.updateUser(user, id);

	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return userService.createUser(user);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable String id) {
		return userService.deleteUser(id);
	}
}
