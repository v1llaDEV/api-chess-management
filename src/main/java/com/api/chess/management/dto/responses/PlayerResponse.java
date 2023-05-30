package com.api.chess.management.dto.responses;

import com.api.chess.management.entity.Country;

import lombok.Data;

@Data
public class PlayerResponse {

	private Long id;
	private String name;
	private String dateBirthday;
	private Country country;
}
