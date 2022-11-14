package com.api.chess.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.entity.Openning;
import com.api.chess.management.repository.OpenningRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(ConfigurationConstants.OPENNING_API_URL)
@Api(value="Opening", tags = "Opening")
public class OpenningController {

	@Autowired
	private OpenningRepository openningRepository;
	
	@GetMapping("")
	public List<Openning> getAllOpenning(){
		return openningRepository.findAll();
	}
}
