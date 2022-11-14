package com.api.chess.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.chess.management.constants.ConfigurationConstants;
import com.api.chess.management.entity.Result;
import com.api.chess.management.repository.ResultRepository;

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
