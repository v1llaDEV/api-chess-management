package com.api.chess.management.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class Openning.
 */
@Entity
@Table(name = "apertura", schema = "public")
public class Openning implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6502947419501620832L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String id;

	/** The name. */
	@Column(name = "nombre")
	private String name;
	
	/** The name. */
	@Column(name = "small_name")
	private String smallName;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
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
	 * Gets the small name.
	 *
	 * @return the small name
	 */
	public String getSmallName() {
		return smallName;
	}

	/**
	 * Sets the small name.
	 *
	 * @param smallName the new small name
	 */
	public void setSmallName(String smallName) {
		this.smallName = smallName;
	}
	
	

}
