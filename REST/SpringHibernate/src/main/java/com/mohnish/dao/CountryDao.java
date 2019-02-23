package com.mohnish.dao;

import java.util.List;

import com.mohnish.model.Country;

public interface CountryDao {

	public List<Country> getAllCountries();

	public Country getCountry(int id);

}
