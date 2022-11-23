package com.api.chess.management.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.chess.management.entity.User;

public interface UserService {

	public List<User> getAllUsers();

	public User getUserBydId(@PathVariable String id);

	public User updateUser(@RequestBody User user, @PathVariable String id);

	public User createUser(@RequestBody User user);

	public void deleteUser(@PathVariable String id);
}
