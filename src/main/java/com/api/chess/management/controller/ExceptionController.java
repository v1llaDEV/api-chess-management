package com.api.chess.management.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.api.chess.management.dto.responses.ErrorResponse;
import com.api.chess.management.exception.GeneralException;
import com.api.chess.management.exception.ResourceAlreadyExistsException;
import com.api.chess.management.exception.ResourceNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Class ExceptionController.
 */
@ControllerAdvice
public class ExceptionController{

  /**
   * Resource not found exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    ErrorResponse message = new ErrorResponse(
        HttpStatus.NOT_FOUND.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    
    return new ResponseEntity<ErrorResponse>(message, HttpStatus.NOT_FOUND);
  }
  
  /**
   * Resource already exists exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> resourceAlreadyExistsException(ResourceAlreadyExistsException ex, WebRequest request) {
    ErrorResponse message = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    
    return new ResponseEntity<ErrorResponse>(message, HttpStatus.BAD_REQUEST);
  }
  
  /**
   * General exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(GeneralException.class)
  public ResponseEntity<ErrorResponse> generalException(GeneralException ex, WebRequest request) {
    ErrorResponse message = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    
    return new ResponseEntity<ErrorResponse>(message, HttpStatus.BAD_REQUEST);
  }
  
  /**
   * Method not allowed exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponse> methodNotAllowedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
    ErrorResponse message = new ErrorResponse(
        HttpStatus.METHOD_NOT_ALLOWED.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    
    return new ResponseEntity<ErrorResponse>(message, HttpStatus.METHOD_NOT_ALLOWED);
  }
  
  /**
   * General exception.
   *
   * @param ex the ex
   * @param request the request
   * @return the response entity
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> generalException(Exception ex, WebRequest request) {
    ErrorResponse message = new ErrorResponse(
        HttpStatus.SERVICE_UNAVAILABLE.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    
    return new ResponseEntity<ErrorResponse>(message, HttpStatus.SERVICE_UNAVAILABLE);
  }
}
