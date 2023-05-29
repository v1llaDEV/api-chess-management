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

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 */
@Entity
@Table(name = "partida", schema = "public")
public class Game implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -248797665640226032L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/** The white player. */
	@ManyToOne
	@JoinColumn(name = "id_jugador_Blancas")
	private Player whitePlayer;

	/** The black player. */
	@ManyToOne
	@JoinColumn(name = "id_jugador_Negras")
	private Player blackPlayer;

	/** The result. */
	@ManyToOne
	@JoinColumn(name = "id_resultado")
	private Result result;

	/** The movements counter. */
	@Column(name = "movimientos")
	private Integer movementsCounter;

	/** The year. */
	@Column(name = "ano")
	private Integer year;

	/** The opening. */
	@ManyToOne
	@JoinColumn(name = "id_apertura")
	private Openning opening;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the white player.
	 *
	 * @return the white player
	 */
	public Player getWhitePlayer() {
		return whitePlayer;
	}

	/**
	 * Sets the white player.
	 *
	 * @param whitePlayer the new white player
	 */
	public void setWhitePlayer(Player whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	/**
	 * Gets the black player.
	 *
	 * @return the black player
	 */
	public Player getBlackPlayer() {
		return blackPlayer;
	}

	/**
	 * Sets the black player.
	 *
	 * @param blackPlayer the new black player
	 */
	public void setBlackPlayer(Player blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public Result getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(Result result) {
		this.result = result;
	}

	/**
	 * Gets the movements counter.
	 *
	 * @return the movements counter
	 */
	public Integer getMovementsCounter() {
		return movementsCounter;
	}

	/**
	 * Sets the movements counter.
	 *
	 * @param movementsCounter the new movements counter
	 */
	public void setMovementsCounter(Integer movementsCounter) {
		this.movementsCounter = movementsCounter;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * Gets the opening.
	 *
	 * @return the opening
	 */
	public Openning getOpening() {
		return opening;
	}

	/**
	 * Sets the opening.
	 *
	 * @param opening the new opening
	 */
	public void setOpening(Openning opening) {
		this.opening = opening;
	}

}
