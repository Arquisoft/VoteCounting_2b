package es.uniovi.asw.voterResult.webService;

import java.util.Date;
import java.util.List;

import es.uniovi.asw.dbManagement.model.Election;
import es.uniovi.asw.dbManagement.persistence.Repository;
import es.uniovi.asw.voterResult.ElectionsSearch;


public class ElectionSearch implements ElectionsSearch
{
	
	@Override
	public List<Election> findByStartDate(Date startName)
	{
		return 
	}
	
	
	@Override
	public List<Election> findByExpiryDate(Date expiryDate)
	{
		return Repository.electionR.findByExpiryDate(expiryDate);
	}
	
	
	@Override
	public List<Election> findByContainingNameAndStartDate(String name, Date startDate)
	{
		return Repository.electionR.findByNameContainingAndStartDate(name, startDate);
	}
	
	
	@Override
	public List<Election> findByContainingNameAndStartDateAndExpiryDate(String name, Date startDate, Date expiryDay)
	{
		return Repository.electionR.findByNameContainingAndStartDateAndExpiryDate(name, startDate, expiryDay);
	}
	
	
	@Override
	public List<Election> findByStartDateAndExpiryDate(Date startDate, Date expiryDay)
	{
		return Repository.electionR.findByStartDateAndExpiryDate(startDate, expiryDay);
	}
}