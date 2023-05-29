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
 * The Class Player.
 */
@Entity
@Table(name = "jugador", schema = "public")
public class Player implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2173158075389349558L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/** The name. */
	@Column(name = "nombre")
	private String name;

	/** The date birthday. */
	@Column(name = "fecha_nacimiento")
	private String dateBirthday;
	
	/** The country. */
	@ManyToOne
	@JoinColumn(name = "id_pais")
	private Country country;

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
	 * Gets the date birthday.
	 *
	 * @return the date birthday
	 */
	public String getDateBirthday() {
		return dateBirthday;
	}

	/**
	 * Sets the date birthday.
	 *
	 * @param dateBirthday the new date birthday
	 */
	public void setDateBirthday(String dateBirthday) {
		this.dateBirthday = dateBirthday;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(Country country) {
		this.country = country;
	}
	
	
}
