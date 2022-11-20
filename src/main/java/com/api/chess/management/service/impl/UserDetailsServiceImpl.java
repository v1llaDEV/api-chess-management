package com.api.chess.management.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.chess.management.entity.Rol;
import com.api.chess.management.entity.User;
import com.api.chess.management.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username).orElse(null);
		if (user == null) {
			throw new RuntimeException("No se ha encontrado el usuario con username: " + username);
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Rol rol : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(rol.getName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

}
