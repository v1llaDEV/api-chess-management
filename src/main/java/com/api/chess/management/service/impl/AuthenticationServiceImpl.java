package com.api.chess.management.service.impl;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.api.chess.management.dto.UserLogin;
import com.api.chess.management.dto.responses.AuthenticationResponse;
import com.api.chess.management.entity.Rol;
import com.api.chess.management.entity.User;
import com.api.chess.management.security.SecurityToken;
import com.api.chess.management.service.AuthenticationService;
import com.api.chess.management.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticationServiceImpl.
 */
@Service

/** The Constant log. */
@Slf4j

/**
 * Instantiates a new authentication service impl.
 *
 * @param userService the user service
 * @param authenticationManager the authentication manager
 * @param securityToken the security token
 */
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
	/**
     * The User service.
     */
    private final UserService userService;

    /**
     * The Authentication manager.
     */
    private final AuthenticationManager authenticationManager;

    /** securityToken. */
    private final SecurityToken securityToken;
    
    /**
     * The Refresh token expire time.
     */
    private Date refreshToken_expire_time;
    
    

    /**
     * Refresh token.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        log.info("Actualizando token");

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(securityToken.getKey());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User userInfo = userService.getUserByUsername(username);

                String access_token = JWT.create()
                        .withSubject(userInfo.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", userInfo.getRoles().stream().map(Rol::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    /**
     * Login login response.
     *
     * @param request      the request
     * @param loginRequest the login request
     * @return the login response
     * @throws BadCredentialsException the bad credentials exception
     */
    public AuthenticationResponse login(HttpServletRequest request, UserLogin loginRequest) throws BadCredentialsException {
        log.info("El usuario es: {}", loginRequest.getUsername());
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Usuario o contraseña incorrectos");
        }
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Algorithm algorithm = Algorithm.HMAC256(securityToken.getKey());
        String access_token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(refreshToken_expire_time)
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        return new AuthenticationResponse(access_token, refresh_token);
    }
}
