package com.api.chess.management.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "partida")
public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -248797665640226032L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "Id_Jugador_Blancas")
	private Player whitePlayer;

	@ManyToOne
	@JoinColumn(name = "Id_Jugador_Negras")
	private Player blackPlayer;

	@ManyToOne
	@JoinColumn(name = "Id_Resultado")
	private Result result;

	@Column(name = "Movimientos")
	private Integer movementsCounter;

	@Column(name = "Ano")
	private Integer year;

	@ManyToOne
	@JoinColumn(name = "Id_Apertura")
	private Openning opening;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(Player whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	public Player getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(Player blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Integer getMovementsCounter() {
		return movementsCounter;
	}

	public void setMovementsCounter(Integer movementsCounter) {
		this.movementsCounter = movementsCounter;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Openning getOpening() {
		return opening;
	}

	public void setOpening(Openning opening) {
		this.opening = opening;
	}

}
