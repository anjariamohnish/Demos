package com.mohnish.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mohnish.dao.CountryDao;
import com.mohnish.model.Country;

@Service
public class CountryService {

	@Autowired
	private CountryDao dao;

	public List<Country> getAllCountries() {
		return dao.getAllCountries();
	}

	@Transactional
	public Country getCountry(int id) {
		return dao.getCountry(id);
	}

}
