package com.api.chess.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.chess.management.entity.Openning;
import com.api.chess.management.repository.OpenningRepository;
import com.api.chess.management.service.OpenningService;

// TODO: Auto-generated Javadoc
/**
 * The Class OpenningServiceImpl.
 */
@Service
public class OpenningServiceImpl implements OpenningService {

	/** The openning repository. */
	@Autowired
	OpenningRepository openningRepository;

	/**
	 * Gets the all opennings.
	 *
	 * @return the all opennings
	 */
	@Override
	public List<Openning> getAllOpennings() {
		return openningRepository.findAll();
	}

}
