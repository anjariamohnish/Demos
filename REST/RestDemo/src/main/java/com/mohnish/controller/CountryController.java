package com.mohnish.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mohnish.model.Country;
import com.mohnish.service.CountryService;

@RestController
@RequestMapping("v1/country")
public class CountryController {
	@Autowired
	private CountryService cservice;

	// Get all countries
	@RequestMapping(value = "/getCountries", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Country> getCountries() {
		List<Country> list = cservice.getAllCountries();
		
		return list;
	}

	// Get a country by id
	@RequestMapping(value = "/getCountry/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Country getCountryById(@PathVariable(name = "id") int id) {
		return cservice.getCountry(id);
	}
}
