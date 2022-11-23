package com.api.chess.management.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionController{

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
        HttpStatus.NOT_FOUND.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    
    return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<ErrorMessage> resourceAlreadyExistsException(ResourceAlreadyExistsException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
        HttpStatus.FORBIDDEN.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    
    return new ResponseEntity<ErrorMessage>(message, HttpStatus.FORBIDDEN);
  }
  
  @ExceptionHandler(GeneralException.class)
  public ResponseEntity<ErrorMessage> generalException(GeneralException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
        HttpStatus.BAD_REQUEST.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    
    return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorMessage> methodNotAllowedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
        HttpStatus.METHOD_NOT_ALLOWED.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    
    return new ResponseEntity<ErrorMessage>(message, HttpStatus.METHOD_NOT_ALLOWED);
  }
  
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> generalException(Exception ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
        HttpStatus.SERVICE_UNAVAILABLE.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    
    return new ResponseEntity<ErrorMessage>(message, HttpStatus.SERVICE_UNAVAILABLE);
  }
}
