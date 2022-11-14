package com.api.chess.management.security;

import java.util.List;

import com.api.chess.management.entity.Rol;

public class JwtResponse {

	private String token;
	private String username;
	private List<Rol> roles;

	public JwtResponse() {

	}

	public JwtResponse(String accessToken, String username, List roles) {
		this.token = accessToken;
		this.username = username;
		this.roles = roles;
	}

}
