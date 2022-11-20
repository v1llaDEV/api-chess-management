package com.api.chess.management.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.dto.UserLogin;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(ConfigurationConstants.AUTHENTICATION_URL)
@Api(value = "Authentication", tags = "Authentication")
public class AuthenticationController {

	@PostMapping
	public void authenticate(@RequestBody UserLogin userLogin) {

	}

}