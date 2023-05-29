package com.api.chess.management.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;

import com.api.chess.management.dto.UserLogin;
import com.api.chess.management.dto.responses.AuthenticationResponse;

// TODO: Auto-generated Javadoc
/**
 * The Interface AuthenticationService.
 */
public interface AuthenticationService {

	/**
	 * Refresh token.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	/**
	 * Login.
	 *
	 * @param request the request
	 * @param loginRequest the login request
	 * @return the authentication response
	 * @throws BadCredentialsException the bad credentials exception
	 */
	public AuthenticationResponse login(HttpServletRequest request, UserLogin loginRequest) throws BadCredentialsException;
}
