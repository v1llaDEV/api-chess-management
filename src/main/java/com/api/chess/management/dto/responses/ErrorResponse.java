package com.api.chess.management.dto.responses;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorResponse.
 */
public class ErrorResponse {
	  
  	/** The status code. */
  	private int statusCode;
	  
  	/** The timestamp. */
  	private Date timestamp;
	  
  	/** The message. */
  	private String message;
	  
  	/** The path. */
  	private String path;

	  /**
  	 * Instantiates a new error response.
  	 *
  	 * @param statusCode the status code
  	 * @param timestamp the timestamp
  	 * @param message the message
  	 * @param path the path
  	 */
  	public ErrorResponse(int statusCode, Date timestamp, String message, String path) {
	    this.statusCode = statusCode;
	    this.timestamp = timestamp;
	    this.message = message;
	    this.path = path;
	  }

	  /**
  	 * Gets the status code.
  	 *
  	 * @return the status code
  	 */
  	public int getStatusCode() {
	    return statusCode;
	  }

	  /**
  	 * Gets the timestamp.
  	 *
  	 * @return the timestamp
  	 */
  	public Date getTimestamp() {
	    return timestamp;
	  }

	  /**
  	 * Gets the message.
  	 *
  	 * @return the message
  	 */
  	public String getMessage() {
	    return message;
	  }

	  /**
  	 * Gets the path.
  	 *
  	 * @return the path
  	 */
  	public String getPath() {
	    return path;
	  }
	}