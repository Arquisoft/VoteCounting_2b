package es.uniovi.asw.business.impl;

import java.util.Date;
import java.util.List;

import es.uniovi.asw.business.ElectionsSearch;
import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.persistence.Repository;


public class ElectionSearch implements ElectionsSearch
{
	@Override
	public List<Election> findByName(String name)
	{
		return Repository.electionR.findByName(name);
	}
	
	
	@Override
	public List<Election> findByStartDate(Date startName)
	{
		return Repository.electionR.findByStartDate(startName);
	}
	
	
	@Override
	public List<Election> findByExpiryDate(Date expiryDate)
	{
		return Repository.electionR.findByExpiryDate(expiryDate);
	}
	
	
	@Override
	public List<Election> findByNameAndStartDate(String name, Date startDate)
	{
		return Repository.electionR.findByNameAndStartDate(name, startDate);
	}
	
	
	@Override
	public List<Election> findByNameAndStartDateAndExpiryDate(String name, Date startDate, Date expiryDay)
	{
		return Repository.electionR.findByNameAndStartDateAndExpiryDate(name, startDate, expiryDay);
	}
	
	
	@Override
	public List<Election> findByStartDateAndExpiryDate(Date startDate, Date expiryDay)
	{
		return Repository.electionR.findByStartDateAndExpiryDate(startDate, expiryDay);
	}
}