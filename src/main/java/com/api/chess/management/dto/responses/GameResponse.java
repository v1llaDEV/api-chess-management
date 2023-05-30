package com.api.chess.management.dto.responses;

import com.api.chess.management.entity.Openning;
import com.api.chess.management.entity.Player;
import com.api.chess.management.entity.Result;

import lombok.Data;

@Data
public class GameResponse {

	private Long id;

	private Player whitePlayer;

	private Player blackPlayer;

	private Result result;

	private Integer movementsCounter;

	private Integer year;

	private Openning opening;
}
