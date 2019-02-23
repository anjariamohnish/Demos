package com.mohnish.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mohnish.model.Country;

@Repository
public class CountryDaoImp implements CountryDao {

	@Autowired
	private SessionFactory factory;

	public List<Country> getAllCountries() {
		List<Country> countries=
		 getSession().createQuery("from Country").list();
		return countries;
	}

	public Country getCountry(int id) {
		return (Country) getSession().get(Country.class, new Integer(id));	
	}

	private Session getSession() {
		Session session = null;
		try {
			session = factory.getCurrentSession(); // if no session then it will throw exception where we will init
													// session
		} catch (HibernateException e) {
			session = factory.openSession();
		}
		return session;
	}

}
