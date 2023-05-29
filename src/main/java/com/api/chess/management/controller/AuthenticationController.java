package com.api.chess.management.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.chess.management.dto.UserLogin;
import com.api.chess.management.dto.responses.AuthenticationResponse;
import com.api.chess.management.service.AuthenticationService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(value = "Authentication", tags = "Authentication")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

   private final AuthenticationService authenticationService;


   /**
    * Refres token.
    *
    * @param request  the request
    * @param response the response
    * @throws IOException the io exception
    */
   @GetMapping("/refresh-token")
   private void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
       authenticationService.refreshToken(request, response);
   }

   /**
    * Login response entity.
    *
    * @param request      the request
    * @param loginRequest the login request
    * @return the response entity
    */
   @PostMapping("/authentication")
   private ResponseEntity<AuthenticationResponse> login(HttpServletRequest request, @RequestBody UserLogin loginRequest) {
	   AuthenticationResponse loginResponse = authenticationService.login(request, loginRequest);
       return ResponseEntity.ok(loginResponse);
   }

}