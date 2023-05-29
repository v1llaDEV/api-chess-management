package com.api.chess.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.chess.management.entity.Result;
import com.api.chess.management.repository.ResultRepository;
import com.api.chess.management.service.ResultService;

// TODO: Auto-generated Javadoc
/**
 * The Class ResultServiceImpl.
 */
@Service
public class ResultServiceImpl implements ResultService {

	/** The result repository. */
	@Autowired
	private ResultRepository resultRepository;

	/**
	 * Gets the all results.
	 *
	 * @return the all results
	 */
	@Override
	public List<Result> getAllResults() {
		return resultRepository.findAll();
	}

}
