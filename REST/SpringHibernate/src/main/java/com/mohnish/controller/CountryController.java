package com.mohnish.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mohnish.model.Country;
import com.mohnish.service.CountryService;

@RestController
@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountryService service;
	
	@RequestMapping(value="/getCountries", method=RequestMethod.GET, headers="Accept=application/json",produces="application/json")
	public ResponseEntity<List<Country>> getCountries() {
        List<Country> list = service.getAllCountries();  
        System.out.print(list.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");   
        return new ResponseEntity<List<Country>>(list,headers,HttpStatus.CREATED);
    }
	
    @RequestMapping(value="/getCountry/{id}", method=RequestMethod.GET, headers="Accept=application/json")
    public Country getCountryById(@PathVariable(value="id") int id) {
        return service.getCountry(id);
    }  
}
