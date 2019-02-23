package com.mohnish.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.mohnish.model.Country;

@Repository
public class CountryDao {

	@Autowired
	private HibernateTemplate template;


	public List<Country> getAllCountries() {
		return template.loadAll(Country.class);
	}

	public Country getCountry(int id) {
		return (Country) template.get(Country.class, new Integer(id));
	}
}
