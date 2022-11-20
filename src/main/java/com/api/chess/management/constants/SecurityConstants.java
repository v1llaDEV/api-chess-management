package com.api.chess.management.constants;

public class SecurityConstants {

	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	public static final String SUPER_SECRET_KEY = "1234";
	public static final long TOKEN_EXPIRATION_TIME = 86400000; // 1 day
	public static final String AUTHORITIES_KEY = "roles";

	public static final String ROL_ADMIN = "ADMIN";
	public static final String ROL_READ_WRITE = "READ/WRITE";
	public static final String ROL_READ = "READ";

}
