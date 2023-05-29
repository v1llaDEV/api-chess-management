package com.api.chess.management.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * To string.
 *
 * @return the java.lang. string
 */
@Data

/**
 * Instantiates a new authentication response.
 *
 * @param access_token the access token
 * @param refresh_token the refresh token
 */
@AllArgsConstructor

/**
 * Instantiates a new authentication response.
 */
@NoArgsConstructor
public class AuthenticationResponse {
	/**
     * The Access token.
     */
    private String access_token;
    /**
     * The Refresh token.
     */
    private String refresh_token;
}
