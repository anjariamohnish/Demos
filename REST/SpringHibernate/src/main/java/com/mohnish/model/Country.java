package com.mohnish.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "COUNTRY")
@Component
public class Country implements Serializable {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "POPULATION")
	private String population;

	public Country() {
	}

	public Country(int id, String name, String population) {
		super();
		this.id = id;
		this.name = name;
		this.population = population;
	}

	private int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	private String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	private String getPopulation() {
		return population;
	}

	private void setPopulation(String population) {
		this.population = population;
	}

}
