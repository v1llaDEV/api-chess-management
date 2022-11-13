package com.gestion.ajedrez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.ajedrez.constants.ConfigurationConstants;
import com.gestion.ajedrez.entity.Result;
import com.gestion.ajedrez.repository.ResultRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(ConfigurationConstants.RESULT_API_URL)
@Api(value="Result", tags = "Result")
public class ResultController {

	@Autowired
	private ResultRepository resultRepository;
	
	@GetMapping("")
	public List<Result> getAllResults(){
		return resultRepository.findAll();
	}
}
