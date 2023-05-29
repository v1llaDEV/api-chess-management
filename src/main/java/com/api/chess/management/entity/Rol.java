package com.api.chess.management.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Auto-generated Javadoc
/**
 * The Class Rol.
 */
@Entity
@Table(name = "rol", schema = "public")
public class Rol implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 682172575923675084L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	@Column(name = "id")
	private Long id;

	/** The name. */
	@Column(name = "nombre")
	private String name;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return this.id;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Instantiates a new rol.
	 *
	 * @param id the id
	 * @param name the name
	 */
	public Rol(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * Instantiates a new rol.
	 */
	public Rol() {
		super();
	}
	
	

}
