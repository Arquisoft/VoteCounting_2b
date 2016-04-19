package es.uniovi.asw.voterResult;

import java.util.Date;
import java.util.List;

import es.uniovi.asw.dbManagement.model.Election;


public interface ElectionsSearch
{
	List<Election> findByContainingName(String name);
	List<Election> findByStartDate(Date startName);
	List<Election> findByExpiryDate(Date expiryDate);
	
	List<Election> findByContainingNameAndStartDate(String name, Date startDate);
	List<Election> findByContainingNameAndStartDateAndExpiryDate(String name, Date startDate, Date expiryDay);
	
	List<Election> findByStartDateAndExpiryDate(Date startDate, Date expiryDay);
}