package com.api.chess.management.constants;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

// TODO: Auto-generated Javadoc
/**
 * The Class SecurityConstants.
 */
public class SecurityConstants {

	/** The Constant HEADER_AUTHORIZACION_KEY. */
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	
	/** The Constant TOKEN_BEARER_PREFIX. */
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	
	/** The Constant TOKEN_EXPIRATION_TIME. */
	public static final Date TOKEN_EXPIRATION_TIME = new Date();// 1 day
	
	/** The Constant AUTHORITIES_KEY. */
	public static final String AUTHORITIES_KEY = "roles";

	/** The Constant ROL_ADMIN. */
	public static final String ROL_ADMIN = "ADMIN";
	
	/** The Constant ROL_READ_WRITE. */
	public static final String ROL_READ_WRITE = "READ/WRITE";
	
	/** The Constant ROL_READ. */
	public static final String ROL_READ = "READ";
	
	/** The jwt secret key. */
	/* FIX CODE TO INYECT PROPERTY STATIC VALUE */
	@Value("${jwt.secret.key}")
	public String JWT_SECRET_KEY;
	
	/** The jwt secret key property name. */
	public static String JWT_SECRET_KEY_PROPERTY_NAME = "JWT_SECRET_KEY";
	
	/**
	 * Sets the jwt secret key.
	 *
	 * @param name the new jwt secret key
	 */
	@Value("${jwt.secret.key}:1234")
	public void setJwtSecretKey(String name) {
		JWT_SECRET_KEY_PROPERTY_NAME = name;
	}
	/* END OF FIX CODE TO INYECT PROPERTY STATIC VALUE */

}
