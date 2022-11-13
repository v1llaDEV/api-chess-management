package com.gestion.ajedrez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.ajedrez.constants.ConfigurationConstants;
import com.gestion.ajedrez.entity.Openning;
import com.gestion.ajedrez.repository.OpenningRepository;

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
