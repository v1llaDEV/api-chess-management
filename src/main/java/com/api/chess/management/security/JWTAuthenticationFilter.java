package com.api.chess.management.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.constants.SecurityConstants;
import com.api.chess.management.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	@Value("${jwt.secret.key}")
	private String jwtSecretKey;

	private static final Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		setFilterProcessesUrl(ConfigurationConstants.AUTHENTICATION_URL);
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		User user = null;

		try {
			user = new ObjectMapper().readValue(request.getInputStream(), User.class);

//			userDetails = userDetailsService.loadUserByUsername(user.getUsername());
		} catch (UsernameNotFoundException | IOException e) {
			e.printStackTrace();
		}
		log.info("Attemp authentication with username: {}", user.getUsername());

		return authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null));

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String token = Jwts.builder()
				.setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
				.claim(SecurityConstants.AUTHORITIES_KEY, auth.getAuthorities())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET_KEY_PROPERTY_NAME).compact();
		response.addHeader(SecurityConstants.HEADER_AUTHORIZACION_KEY,
				SecurityConstants.TOKEN_BEARER_PREFIX + " " + token);

	    addingUserToSecurityContext(request, auth);
		
		log.info("Sucessful authentication with username: {}",
				((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
	}

	private void addingUserToSecurityContext(HttpServletRequest request, Authentication auth) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    securityContext.setAuthentication(auth);

	    // Create a new session and add the security context.
	    HttpSession session = request.getSession(true);
	    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
	}
}
