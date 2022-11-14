package com.api.chess.management.dto;

import java.io.Serializable;

public class UserLogin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2252224575530366149L;
	
	String username;
	String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
