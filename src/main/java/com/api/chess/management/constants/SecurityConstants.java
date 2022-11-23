package com.api.chess.management.constants;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstants {

	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	public static final long TOKEN_EXPIRATION_TIME = 86400000; // 1 day
	public static final String AUTHORITIES_KEY = "roles";

	public static final String ROL_ADMIN = "ADMIN";
	public static final String ROL_READ_WRITE = "READ/WRITE";
	public static final String ROL_READ = "READ";
	
	/* FIX CODE TO INYECT PROPERTY STATIC VALUE */
	@Value("${jwt.secret.key}")
	public String JWT_SECRET_KEY;
	
	public static String JWT_SECRET_KEY_PROPERTY_NAME = "JWT_SECRET_KEY";
	
	@Value("${jwt.secret.key}")
	public void setJwtSecretKey(String name) {
		JWT_SECRET_KEY_PROPERTY_NAME = name;
	}
	/* END OF FIX CODE TO INYECT PROPERTY STATIC VALUE */

}
