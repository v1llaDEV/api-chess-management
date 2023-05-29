package com.api.chess.management.dto;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class UserLogin.
 */
public class UserLogin implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2252224575530366149L;
	
	/** The username. */
	String username;
	
	/** The password. */
	String password;
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
