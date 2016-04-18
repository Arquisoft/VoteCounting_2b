package es.uniovi.asw.business;

import java.util.Date;
import java.util.List;

import es.uniovi.asw.dbManagement.model.Election;


public interface ElectionsSearch
{
	List<Election> findByName(String name);
	List<Election> findByStartDate(Date startName);
	List<Election> findByExpiryDate(Date expiryDate);
	
	List<Election> findByNameAndStartDate(String name, Date startDate);
	List<Election> findByNameAndStartDateAndExpiryDate(String name, Date startDate, Date expiryDay);
	
	List<Election> findByStartDateAndExpiryDate(Date startDate, Date expiryDay);
}