package com.api.chess.management.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.chess.management.entity.Users;

public interface UserService {

	public List<Users> getAllUsers();

	public Users getUserBydId(@PathVariable String id);

	public Users updateUser(@RequestBody Users user, @PathVariable String id);

	public Users createUser(@RequestBody Users user);

	public void deleteUser(@PathVariable String id);
}
